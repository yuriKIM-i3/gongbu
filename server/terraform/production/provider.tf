terraform {
  required_version = ">= 1.1.3"

  backend "s3" {
    bucket  = "gongbu-prd-tf"
    key     = "prd"
    region  = "ap-northeast-1"
    profile = "gongbu_prd"
  }

  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = ">= 4.9.0"
    }
  }
}

provider "aws" {
  region  = "ap-northeast-1"
  profile = "gongbu_prd"
}