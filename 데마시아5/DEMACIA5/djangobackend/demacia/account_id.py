import requests
import json
import pandas as pd

summonarNames = []
#league-V4
league_v4_url = "https://kr.api.riotgames.com/lol/league/v4/challengerleagues/by-queue/RANKED_SOLO_5x5?api_key=RGAPI-4dcd2099-2605-4440-9864-f53a305141e7"
results = requests.get(league_v4_url).json()["entries"]
for i in range(len(results)):
    summonarNames.append(results[i]["summonerName"])


#account _id 얻기
#summoner-V4-byname
account_ids = []
for summonarname in summonarNames:
    name = summonarname
    summoner_v4_byname = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+ str(name)+ "?api_key=RGAPI-4dcd2099-2605-4440-9864-f53a305141e7"
    results = requests.get(summoner_v4_byname).json()["accountId"]
    account_ids.append(str(results))

# account_ids_data = json.dumps(account_ids)
