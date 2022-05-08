[
    {
        "name": "${container_name}",
        "image": "${ecr_repository_url}:latest",
        "essential": true,
        "portMappings": [
            {
                "protocol": "tcp",
                "containerPort": 80,
                "hostPort": 80
            }
        ],
        "logConfiguration": {
            "logDriver": "awslogs",
            "options": {
                "awslogs-group": "${log_group}",
                "awslogs-region": "ap-northeast-1",
                "awslogs-stream-prefix": "ecs"
            }
        },
        "environment": [
            {
                "name": "SPRING_PROFILES_ACTIVE",
                "value": "${sb_env}"
            },
            {
                "name": "FLYWAY_URL",
                "value": "${flyway_url}"
            },
            {
                "name": "FLYWAY_USER",
                "value": "${flyway_user}"
            },
            {
                "name": "FLYWAY_PASSWORD",
                "value": "${flyway_password}"
            },
            {
                "name": "FLYWAY_LOCATIONS",
                "value": "${flyway_locations}"
            }
        ],
        "entryPoint": [
            "bash",
            "-c",
            "${docker_endpoint_sh}"
        ]
    }
]