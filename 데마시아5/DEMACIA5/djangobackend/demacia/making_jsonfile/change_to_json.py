import csv
import json

with open('champion_stats.csv') as f:
       reader = csv.DictReader(f)
       rows = list(reader)

with open('champion_stats.json', 'w') as f:
       json.dump(rows, f)