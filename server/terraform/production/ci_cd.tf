module iam_codepipeline {
  source                    = "../modules/iam/codepipeline"

  app_name                  = var.app_name
  s3_pipeline_artifact_id   = aws_s3_bucket.pipeline_artifact.id
  s3_codebuild_buildspec_id = aws_s3_bucket.pipeline_src.id
}

module iam_codebuild {
  source                   = "../modules/iam/codebuild"

  app_name                 = var.app_name
  s3_pipeline_artifact_arn = aws_s3_bucket.pipeline_artifact.arn
  cw_logs_codebuild_arn    = aws_cloudwatch_log_group.codebuild.arn
}

resource "aws_codepipeline" "main" {
  name     = var.app_name
  role_arn = module.iam_codepipeline.role.arn

  artifact_store {
    type     = "S3"
    location = aws_s3_bucket.pipeline_artifact.id
  }

  stage {
    name = "Source"
    # https://docs.aws.amazon.com/codepipeline/latest/userguide/action-reference-S3.html
    action {
      category         = "Source"
      configuration    = {
        "PollForSourceChanges" = true
        "S3Bucket"             = aws_s3_bucket.pipeline_src.bucket
        "S3ObjectKey"          = "build.zip"
      }
      name             = "s3_source"
      output_artifacts = ["s3_source"]
      owner            = "AWS"
      provider         = "S3"
      run_order        = 1
      version          = "1"
    }
  }

  stage {
    name = "Build"
    action {
      name             = "Build"
      category         = "Build"
      owner            = "AWS"
      provider         = "CodeBuild"
      version          = "1"
      run_order        = 2
      input_artifacts  = ["s3_source"]
      output_artifacts = ["build_out"]
      configuration    = {
        ProjectName = var.app_name
      }
    }
  }

  stage {
    name = "Deploy"
    action {
      name            = "Deploy"
      category        = "Deploy"
      owner           = "AWS"
      provider        = "CodeDeployToECS"
      version         = "1"
      run_order       = 3
      input_artifacts = ["build_out"]
      configuration   = {
        ApplicationName                = aws_codedeploy_app.main.name
        DeploymentGroupName            = aws_codedeploy_deployment_group.main.deployment_group_name
        TaskDefinitionTemplateArtifact = "build_out"
        TaskDefinitionTemplatePath     = "task_definition.json"
        AppSpecTemplateArtifact        = "build_out"
        AppSpecTemplatePath            = "appspec.yaml"
      }
    }
  }
}

resource "aws_s3_bucket" "pipeline_artifact" {
  bucket        = "${replace(var.app_name, "_", "-")}-pipeline-artifact"
  force_destroy = true
}

resource "aws_s3_bucket_acl" "pipeline_artifact_bucket_acl" {
  bucket = aws_s3_bucket.pipeline_artifact.id
  acl    = "private"
}

resource "aws_s3_bucket" "pipeline_src" {
  bucket        = "${replace(var.app_name, "_", "-")}-pipeline-src"
  force_destroy = true
}

resource "aws_s3_bucket_acl" "pipeline_src_bucket_acl" {
  bucket = aws_s3_bucket.pipeline_src.id
  acl    = "private"
}

resource "aws_s3_bucket_versioning" "pipeline_src_versioning" {
  bucket = aws_s3_bucket.pipeline_src.id
  versioning_configuration {
    status = "Enabled"
  }
}

resource "aws_codebuild_project" "main" {
  name          = var.app_name
  description   = "s3"
  build_timeout = 60
  service_role  = module.iam_codebuild.role.arn

  artifacts {
    type = "CODEPIPELINE"
  }

  logs_config {
    cloudwatch_logs {
      status     = "ENABLED"
      group_name = aws_cloudwatch_log_group.codebuild.name
    }
    s3_logs {
      status = "DISABLED"
    }
  }

  source {
    type      = "CODEPIPELINE"
    buildspec = var.buildspec_file_path
  }

  environment {
    compute_type                = "BUILD_GENERAL1_SMALL"
    image                       = "aws/codebuild/standard:4.0"
    type                        = "LINUX_CONTAINER"
    image_pull_credentials_type = "CODEBUILD"
    privileged_mode             = true

    environment_variable {
      name  = "APP_NAME"
      value = var.app_name
    }
    environment_variable {
      name = "SB_DATASOURCE_URL"
      value = aws_ssm_parameter.global_values["sb_datasource_url"].value
    }
    environment_variable {
      name = "SB_DATASOURCE_USERNAME"
      value = aws_ssm_parameter.global_values["sb_datasource_username"].value
    }
    environment_variable {
      name = "SB_DATASOURCE_PASSWORD"
      value = aws_ssm_parameter.global_values["sb_datasource_password"].value
    }
    environment_variable {
      name = "SB_JPA_DATABASE"
      value = aws_ssm_parameter.global_values["sb_jpa_database"].value
    }
    environment_variable {
      name = "SB_JPA_PROPERTIES_HIBERNATE_DIALECT"
      value = aws_ssm_parameter.global_values["sb_jpa_properties_hibernate_dialect"].value
    }
    environment_variable {
      name = "SB_JPA_SHOW_SQL"
      value = aws_ssm_parameter.global_values["sb_jpa_show_sql"].value
    }
    environment_variable {
      name = "SERVER_PORT"
      value = aws_ssm_parameter.global_values["server_port"].value
    }
    environment_variable {
      name = "SERVER_TOMCAT_REMOTE_IP_HEADER"
      value = aws_ssm_parameter.global_values["server_tomcat_remote_ip_header"].value
    }
    environment_variable {
      name = "SERVER_TOMCAT_PROTOCOL_HEADER"
      value = aws_ssm_parameter.global_values["server_tomcat_protocol_header"].value
    }
    environment_variable {
      name = "LOGGING_CONFIG"
      value = aws_ssm_parameter.global_values["logging_config"].value
    }
    environment_variable {
      name = "GOOGLE_CLIENT_ID"
      value = aws_ssm_parameter.global_values["google_client_id"].value
    }
    environment_variable {
      name = "GOOGLE_CLIENT_SECRET"
      value = aws_ssm_parameter.global_values["google_client_secret"].value
    }
    environment_variable {
      name = "GOOGLE_SCOPE"
      value = aws_ssm_parameter.global_values["google_scope"].value
    }
    environment_variable {
      name = "LINE_AUTHORIZATION_URI"
      value = aws_ssm_parameter.global_values["line_authorization_uri"].value
    }
    environment_variable {
      name = "LINE_TOKEN_URI"
      value = aws_ssm_parameter.global_values["line_token_uri"].value
    }
    environment_variable {
      name = "LINE_USER_INFO_URI"
      value = aws_ssm_parameter.global_values["line_user_info_uri"].value
    }
    environment_variable {
      name = "LINE_USER_INFO_AUTHENTICATION_METHOD"
      value = aws_ssm_parameter.global_values["line_user_info_authentication_method"].value
    }
    environment_variable {
      name = "LINE_USER_NAME_ATTRIBUTE"
      value = aws_ssm_parameter.global_values["line_user_name_attribute"].value
    }
    environment_variable {
      name = "LINE_JWK_SET_URI"
      value = aws_ssm_parameter.global_values["line_jwk_set_uri"].value
    }
    environment_variable {
      name = "LINE_PROVIDER"
      value = aws_ssm_parameter.global_values["line_provider"].value
    }
    environment_variable {
      name = "LINE_CLIENT_ID"
      value = aws_ssm_parameter.global_values["line_client_id"].value
    }
    environment_variable {
      name = "LINE_CLIENT_SECRET"
      value = aws_ssm_parameter.global_values["line_client_secret"].value
    }
    environment_variable {
      name = "LINE_CLIENT_AUTHENTICATION_METHOD"
      value = aws_ssm_parameter.global_values["line_client_authentication_method"].value
    }
    environment_variable {
      name = "LINE_AUTHORIZATION_GRANT_TYPE"
      value = aws_ssm_parameter.global_values["line_authorization_grant_type"].value
    }
    environment_variable {
      name = "LINE_REDIRECT_URI"
      value = aws_ssm_parameter.global_values["line_redirect_uri"].value
    }
    environment_variable {
      name = "LINE_SCOPE"
      value = aws_ssm_parameter.global_values["line_scope"].value
    }
    environment_variable {
      name = "LINE_CLIENT_NAME"
      value = aws_ssm_parameter.global_values["line_client_name"].value
    }
    environment_variable {
      name  = "WEB_REPOSITORY_URL"
      value = aws_ecr_repository.main.repository_url
    }
    environment_variable {
      name  = "ECS_FAMILY"
      value = aws_ecs_task_definition.main.family
    }
    environment_variable {
      name  = "SB_CONTAINER_NAME"
      value = var.app_name
    }
  }
}


resource "aws_cloudwatch_log_group" "codebuild" {
  name = "/codebuild/${var.app_name}"
}

module iam_codedeploy {
  source   = "../modules/iam/codedeploy"

  app_name = var.app_name
}

resource "aws_codedeploy_app" "main" {
  compute_platform = "ECS"
  name             = var.app_name
}

resource "aws_codedeploy_deployment_group" "main" {
  deployment_group_name  = var.app_name
  deployment_config_name = "CodeDeployDefault.ECSAllAtOnce"
  app_name               = aws_codedeploy_app.main.name
  service_role_arn       = module.iam_codedeploy.role.arn
  
  auto_rollback_configuration {
    enabled = true
    events  = [
      "DEPLOYMENT_FAILURE"
    ]
  }

  blue_green_deployment_config {
    deployment_ready_option {
      action_on_timeout = "CONTINUE_DEPLOYMENT"
    }

    terminate_blue_instances_on_deployment_success {
      action                           = "TERMINATE"
      termination_wait_time_in_minutes = 1
    }
  }

  deployment_style {
    deployment_option = "WITH_TRAFFIC_CONTROL"
    deployment_type   = "BLUE_GREEN"
  }

  ecs_service {
    cluster_name = aws_ecs_cluster.main.name
    service_name = aws_ecs_service.main.name
  }

  load_balancer_info {
    target_group_pair_info {
      prod_traffic_route {
        listener_arns = [
          aws_lb_listener.main.arn
        ]
      }
      target_group {
        name = aws_lb_target_group.blue.name
      }
      target_group {
        name = aws_lb_target_group.green.name
      }
    }
  }
}