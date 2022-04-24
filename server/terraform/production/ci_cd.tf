# module.iam_codepipeline.role
module iam_codepipeline {
  source                    = "../modules/iam/codepipeline"

  app_name                  = var.app_name
  s3_pipeline_artifact_id   = aws_s3_bucket.pipeline_artifact.id
  s3_codebuild_buildspec_id = aws_s3_bucket.pipeline_src.id
}

# module.iam_codebuild.role
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
  }
}


resource "aws_cloudwatch_log_group" "codebuild" {
  name = "/codebuild/${var.app_name}"
}

# resource "aws_cloudwatch_log_group" "ecs_task_definitions" {
#   name = "/ecs/task-definitions/${var.app_name}"
# }

# resource "aws_cloudwatch_log_group" "ecs_task_definitions_nginx" {
#   name = "/ecs/task-definitions/${var.app_name}-nginx"
# }