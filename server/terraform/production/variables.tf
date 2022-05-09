variable app_name {
  type = string
  default = "gongbu_app"
}

variable buildspec_file_path {
  type = string
  default = "./server/buildspec.yml"
}

# spring boot application-prd.properties
# デフォルト値は環境変数で設定
variable sb_datasource_url {
  type = string
  default  = ""
}

variable sb_datasource_username {
  type = string
  default  = ""
}

variable sb_datasource_password {
  type = string
  default  = ""
}

variable sb_jpa_database {
  type = string
  default  = ""
}

variable sb_jpa_properties_hibernate_dialect {
  type = string
  default  = ""
}

variable sb_jpa_show_sql {
  type = string
  default  = ""
}

variable server_port {
  type = string
  default  = ""
}

variable logging_config {
  type = string
  default  = ""
}

variable google_client_id {
  type = string
  default  = ""
}

variable google_client_secret {
  type = string
  default  = ""
}

variable google_scope {
  type = string
  default  = ""
}

variable line_authorization_uri {
  type = string
  default  = ""
}

variable line_token_uri {
  type = string
  default  = ""
}

variable line_user_info_uri {
  type = string
  default  = ""
}

variable line_user_info_authentication_method {
  type = string
  default  = ""
}

variable line_user_name_attribute {
  type = string
  default  = ""
}

variable line_jwk_set_uri {
  type = string
  default  = ""
}

variable line_provider {
  type = string
  default  = ""
}

variable line_client_id {
  type = string
  default  = ""
}

variable line_client_secret {
  type = string
  default  = ""
}

variable line_client_authentication_method {
  type = string
  default  = ""
}

variable line_authorization_grant_type {
  type = string
  default  = ""
}

variable line_redirect_uri {
  type = string
  default  = ""
}

variable line_scope {
  type = string
  default  = ""
}

variable line_client_name {
  type = string
  default  = ""
}

locals {
  sb_props = {
    sb_datasource_url = var.sb_datasource_url
    sb_datasource_username = var.sb_datasource_username
    sb_datasource_password = var.sb_datasource_password
    sb_jpa_database = var.sb_jpa_database
    sb_jpa_properties_hibernate_dialect = var.sb_jpa_properties_hibernate_dialect 
    sb_jpa_show_sql = var.sb_jpa_show_sql
    server_port = var.server_port
    logging_config = var.logging_config
    google_client_id = var.google_client_id
    google_client_secret = var.google_client_secret
    google_scope = var.google_scope
    line_authorization_uri = var.line_authorization_uri
    line_token_uri = var.line_token_uri
    line_user_info_uri = var.line_user_info_uri
    line_user_info_authentication_method = var.line_user_info_authentication_method
    line_user_name_attribute = var.line_user_name_attribute
    line_jwk_set_uri = var.line_jwk_set_uri
    line_provider = var.line_provider
    line_client_id = var.line_client_id
    line_client_secret = var.line_client_secret
    line_client_authentication_method = var.line_client_authentication_method
    line_authorization_grant_type = var.line_authorization_grant_type
    line_redirect_uri = var.line_redirect_uri
    line_scope = var.line_scope
    line_client_name = var.line_client_name
  }
}

variable env {
  type = string
  default  = "prd"
}

variable vpc_cidr {
  type = string
  default  = "10.1.0.0/16"
}

variable db_name {
  type = string
  default  = "gongbu_prd"
}

variable domain_name {
  type = string
  default  = "gongbu.jp"
}