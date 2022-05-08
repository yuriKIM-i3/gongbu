import json
import os

with open('origin_task_definition.json', 'r') as f:
    json_data = json.load(f)

task_def_to_delete = ['taskDefinitionArn', 'revision', 'status', 'requiresAttributes', 'placementConstraints', 'compatibilities', 'registeredAt', 'registeredBy']
for i in task_def_to_delete: 
    json_data['taskDefinition'].pop(i)

with open(os.environ['EDITED_TASK_DEF'],'w') as f:
    json.dump(json_data['taskDefinition'], f, ensure_ascii=False)