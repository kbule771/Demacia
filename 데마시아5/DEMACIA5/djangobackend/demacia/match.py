import requests
import json
import time

game_ids = []
lane_dict = {}
champion_info = dict()

account_id = "VWNSbDLytNV47o6jJ0E9TtD5OEPG3ENocgp9Pro9Y3iQ6Tc"
match_v4_accountid = "https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/"+str(account_id)+"?api_key=RGAPI-4dcd2099-2605-4440-9864-f53a305141e7"
account_results = requests.get(match_v4_accountid).json()["matches"]
# print(results)

cnt = 0
for i in range(len(account_results)):
    if cnt == 80:
        break
    game_ids.append(account_results[i]["gameId"])
    # lane dict 만들기
    lane = account_results[i]['lane']
    lane_dict.setdefault(lane, 0)
    lane_dict[lane] += 1
    cnt += 1
game_ids = list(set(game_ids))
# print(game_ids)
# print(lane_dict)

for game_id in game_ids:
    match_v4_gameId ="https://kr.api.riotgames.com/lol/match/v4/matches/"+str(game_id)+"?api_key=RGAPI-4dcd2099-2605-4440-9864-f53a305141e7"
    game_results = requests.get(match_v4_gameId).json()
    # participantId 얻기
    for parts in game_results["participantIdentities"]:
        if parts["player"]["accountId"] == account_id:
            partId = parts["participantId"]
            break
    
    # character 정보, 이겼는지 졌는지 표시
    for part in game_results["participants"]:
        if part["participantId"] == partId:
            chamId = part["championId"]
            champion_info.setdefault(chamId, [0, 0])
            if part["stats"]["win"] == True:
                champion_info[chamId][0] += 1
            else:
                champion_info[chamId][1] += 1
            break

# print(champion_info)

champion_winnig_rate = dict()
real_cham_dict = dict()
for champion_id in champion_info:
    champion_winnig_rate[champion_id] = champion_info[champion_id][0] / sum(champion_info[champion_id])

print(champion_winnig_rate)
champion_winnig_rate = sorted(champion_winnig_rate.items(), key=lambda x:x[1], reverse=True)


for idx in range(len(champion_winnig_rate)):
    if idx > 2:
        break
    else:
        real_cham_dict[str(champion_winnig_rate[idx][0])] = str(champion_winnig_rate[idx][1])

### 승률 dict
print(real_cham_dict)

### 최고 lane
lane_dict = sorted(lane_dict.items(), key=lambda x:x[1], reverse=True)
lane = lane_dict[0][0]
print(lane)