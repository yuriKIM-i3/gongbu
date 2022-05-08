resource "aws_ecr_repository" "main" {
  name                 = var.app_name
  image_tag_mutability = "MUTABLE"
}

resource "aws_ecs_cluster" "main" {
  name               = "${var.app_name}-cluster"
  capacity_providers = ["FARGATE"]
}

data "aws_ecs_task_definition" "main" {
  task_definition = aws_ecs_task_definition.main.family
}

resource "aws_ecs_service" "main" {
  name                              = "${var.app_name}-service"
  cluster                           = aws_ecs_cluster.main.id
  task_definition                   = "${aws_ecs_task_definition.main.family}:${max(aws_ecs_task_definition.main.revision, data.aws_ecs_task_definition.main.revision)}"
  launch_type                       = "FARGATE"
  desired_count                     = 1
  health_check_grace_period_seconds = 360

  load_balancer {
    target_group_arn = aws_lb_target_group.green.arn
    container_name   = var.app_name
    container_port   = 80
  }

  network_configuration {
    subnets          = [
      aws_subnet.public["ap-northeast-1a"].id,
      aws_subnet.public["ap-northeast-1c"].id
    ]

    security_groups  = [aws_security_group.service.id]
    assign_public_ip = true
  }

  deployment_controller {
    type = "CODE_DEPLOY"
  }

  lifecycle {
    ignore_changes = [
      health_check_grace_period_seconds,
      task_definition,
      load_balancer
    ]
  }

  depends_on                        = [
    aws_lb_target_group.green,
    aws_db_instance.pg_db
  ]
}


resource "aws_cloudwatch_log_group" "ecs_task_definitions" {
  name = "/ecs/${var.app_name}"
}

locals {
  flyway_url = aws_ssm_parameter.global_values["sb_datasource_url"].value
  flyway_user = aws_ssm_parameter.global_values["sb_datasource_username"].value
  flyway_password = aws_ssm_parameter.global_values["sb_datasource_password"].value
  flyway_locations = "filesystem:src/main/resources/db/migration"
}

data "template_file" "container_def" {
  template = file("./container_def.tpl")

  vars     = {
    container_name     = var.app_name
    ecr_repository_url = aws_ecr_repository.main.repository_url
    log_group          = aws_cloudwatch_log_group.ecs_task_definitions.name
    sb_env             = var.env
    flyway_url         = local.flyway_url
    flyway_user        = local.flyway_user
    flyway_password    = local.flyway_password
    flyway_locations   = local.flyway_locations
    docker_endpoint_sh = "sh ./server/entrypoint.sh"
  }
}

resource "aws_ecs_task_definition" "main" {
  family                   = "${var.app_name}-family"
  network_mode             = "awsvpc"
  execution_role_arn       = module.iam_ecs_task_execute.role.arn
  task_role_arn            = module.iam_ecs_task.role.arn
  cpu                      = "512"
  memory                   = "1024"

  requires_compatibilities = ["FARGATE"]

  container_definitions    = data.template_file.container_def.rendered
}

module iam_ecs_task_execute {
  source   = "../modules/iam/ecs_task_execute"

  app_name = var.app_name
}

module iam_ecs_task {
  source   = "../modules/iam/ecs_task"

  app_name = var.app_name
}

resource "aws_security_group" "service" {
  vpc_id      = aws_vpc.main.id
  name        = "${var.app_name}-service"
  description = "${var.app_name}-service"

  ingress {
    from_port       = 80
    to_port         = 80
    protocol        = "tcp"
    security_groups = [aws_security_group.lb.id]
    cidr_blocks     = ["0.0.0.0/0"]
  }

  ingress {
    from_port       = 443
    to_port         = 443
    protocol        = "tcp"
    security_groups = [aws_security_group.lb.id]
    cidr_blocks     = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}