import requests
import json
from pprint import pprint
import numpy
import copy
import pickle

# 닉네임 불러오기
# summonarNames = []
# # league-V4
# league_v4_url = "https://kr.api.riotgames.com/lol/league/v4/challengerleagues/by-queue/RANKED_SOLO_5x5?api_key=RGAPI-4dcd2099-2605-4440-9864-f53a305141e7"
# results = requests.get(league_v4_url).json()["entries"]
# for i in range(len(results)):
#     summonarNames.append(results[i]["summonerName"])

# with open('summonarNames.json', 'w', encoding="UTF-8") as make_file:
#     json.dump(summonarNames, make_file, indent="\t")

# # account id 얻기
# with open('summonarNames.json', 'r') as names:
#     names_list = json.load(names)
#     account_ids = []
#     cnt = 0
#     for summonarname in names_list:
#         # if cnt == 90:
#         #     print(summonarname)
#         #     with open('accountid.json', 'w', encoding="UTF-8") as make_file:
#         #         json.dump(account_ids, make_file, indent="\t")
#         #         break

#         name = summonarname
#         print(name)
#         # summoner_v4_byname = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+ str(name)+ "?api_key=RGAPI-aa7d304b-1def-416a-a85c-3a89c8752017"
#         summoner_v4_byname = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+ str(name)+ "?api_key=RGAPI-4dcd2099-2605-4440-9864-f53a305141e7"

#         # results = requests.get(summoner_v4_byname).json()
#         # print(results)
#         results = requests.get(summoner_v4_byname).json()["accountId"]
#         print(results)
#         account_ids.append(str(results))
#         cnt += 1

#     with open('accountid.json', 'w', encoding="UTF-8") as make_file:
#         json.dump(account_ids, make_file, indent="\t")


# # game id 얻기
# with open('gameid.json', 'r') as gameids:
#     # 원래 있던 gameid 값 가져오기
#     game_ids = json.load(gameids)    

#     with open('accountid.json', 'r') as accounts:
#         account_ids = json.load(accounts)
        # cnt = 0
        # for account_id in account_ids:
        #     if cnt == 50:
        #         print(account_id)
        #         game_ids = list(set(game_ids))

        #         with open('gameid.json', 'w', encoding="UTF-8") as make_file:
        #             json.dump(game_ids, make_file, indent="\t")
        #             break
        # for account_id in account_ids:
        #     match_v4_accountid = "https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/"+str(account_id)+"?api_key=RGAPI-4dcd2099-2605-4440-9864-f53a305141e7"
        #     results = requests.get(match_v4_accountid).json()["matches"]
        #     # cnt += 1
        #     print(account_id)
        #     for i in range(len(results)):
        #         game_ids.append(results[i]["gameId"])

        # game_ids = list(set(game_ids)) # game_Ids 집합으로 해야함

        # with open('gameid.json', 'w', encoding="UTF-8") as make_file:
        #     json.dump(game_ids, make_file, indent="\t")


# champion_info = dict() # "timo" : [이긴횟수, 진횟수]
# champion id 별 이긴횟수, 진횟수 딕셔너리 만들기
# 2차원 배열로 그룹 추천 
# champion_group_2_array = [[0]*877 for _ in range(877)]
# match-v4-gameid


# 원래 있던 champion_info, champion_group_2_array 값 가져오기
with open('dictandcombo.p', 'rb') as file:
    champion_info = pickle.load(file)
    champion_group_2_array = pickle.load(file)


# with open('gameid.json', 'r') as gameids:
#     # 원래 있던 gameid 값 가져오기
#     game_ids = json.load(gameids) 


#     cnt = 0
#     for game_id in game_ids:
#         # print(champion_info)
#         # print(champion_group_2_array)

#         # if cnt == 100:
#         #     print(game_id)
#         #     with open('dictandcombo.p', 'wb') as file:
#         #         pickle.dump(champion_info, file)
#         #         pickle.dump(champion_group_2_array, file)

#         #     with open('winandlose.json', 'w', encoding="UTF-8") as make_file:
#         #         json.dump(champion_info, make_file)

#         #     with open('chammatrix.json', 'w', encoding="UTF-8") as make_file:
#         #         json.dump(champion_group_2_array, make_file)
#         #     break

#         match_v4_gameId ="https://kr.api.riotgames.com/lol/match/v4/matches/"+str(game_id)+"?api_key=RGAPI-4dcd2099-2605-4440-9864-f53a305141e7"
#         results = requests.get(match_v4_gameId).json()["teams"]
#         cnt += 1
#         for team in results:
#             if team["win"] == "Fail":
#                 for champ in team["bans"]:
#                     champid = champ["championId"]
#                     # champion id 별 이긴횟수, 진횟수 딕셔너리 만들기
#                     if champid not in champion_info:
#                         champion_info[champid] = [0,1]
#                     else:
#                         champion_info[champid][1] += 1
                    
                    
#             elif team["win"] == "Win":
#                 indexes = []
#                 for champ in team["bans"]: 
#                     champid = champ["championId"]
#                     if champid not in champion_info:
#                         champion_info[champid] = [1,0]
#                     else:
#                         champion_info[champid][0] += 1
#                     # 2차원 배열 인덱스 만들기
#                     if -1 < champid < 877:
#                         indexes.append(champid)
#                 # 2차원 배열에 하나씩 플러스

#                 for i in indexes:
#                     for j in indexes:
#                         if i != j:
#                             champion_group_2_array[i][j] += 1


#     with open('dictandcombo.p', 'wb') as file:
#         pickle.dump(champion_info, file)
#         pickle.dump(champion_group_2_array, file)

#     with open('winandlose.json', 'w', encoding="UTF-8") as make_file:
#         json.dump(champion_info, make_file)

#     with open('chammatrix.json', 'w', encoding="UTF-8") as make_file:
#         json.dump(champion_group_2_array, make_file)
# 승률 구하기
# champion_winnig_rate = dict()
# for champion_id in champion_info:
#     champion_winnig_rate[champion_id] = champion_info[champion_id][0] / sum(champion_info[champion_id])

# print(champion_winnig_rate)

# 챔피언 dictionary 만들기 
# champ_dictionary = dict()
# for i in range(877):
#     if i not in champ_dictionary and sum(champion_group_2_array[i]) > 0 :
#         champ_dictionary[i] = list(numpy.argsort(champion_group_2_array[i])[::-1][:4])


# 변수 저장
# with open('finaldictcomb.p', 'wb') as file:
#     pickle.dump(champion_winnig_rate, file)
#     pickle.dump(champ_dictionary, file)

# with open('winnigrate.json', 'w', encoding="UTF-8") as make_file:
#     json.dump(champion_winnig_rate, make_file)

# with open('chamcombi.json', 'w', encoding="UTF-8") as make_file:
#     json.dump(champ_dictionary, make_file)

# 저장한 변수 가져오기
with open('finaldictcomb.p', 'rb') as file:
    champion_winnig_rate = pickle.load(file)
    champ_dictionary = pickle.load(file)

print(champ_dictionary)
print(champion_winnig_rate)

# with open('winnigrate.json', 'w', encoding="UTF-8") as make_file:
#     json.dump(champion_winnig_rate, make_file)

# with open('chamcombi.json', 'w', encoding="UTF-8") as make_file:
#     json.dump(champ_dictionary, make_file)

# MBTI 조합
MBTI = {"ENTJ": ['Amumu', 'Chogath', 'Galio', 'Volibear', 'Mordekaiser', 'Zac', 'Gangplank'],
"INFP": ['Shen', 'Malphite', 'Sion', 'Maokai', 'Nasus', 'Ornn', 'Poppy', 'Singed', 'TahmKench', 'Urgot', 'Sett', 'DrMundo' ],
"INTP": [ 'Darius', 'Gnar', 'MonkeyKing', 'Garen', 'Olaf', 'Renekton', 'Warwick', 'Yorick',  'Illaoi', 'Kled', 'Kennen'],
"ENFJ": [ 'JarvanIV', 'Hecarim', 'Skarner', 'Trundle', 'Shyvana', 'RekSai', 'Rammus', 'Nunu', 'Sejuani', 'Gragas'],
"ISFJ": [ 'Syndra', 'Veigar', 'Zoe', 'Velkoz', 'Viktor', 'Xerath', 'Ziggs', 'Azir', 'Malzahar'],
"ISTP": [ 'Anivia', 'Heimerdinger', 'AurelionSol', 'Cassiopeia', 'Karthus', 'TwistedFate',],
"ESFJ": ['Annie',  'Brand', 'Karma', 'Lux', 'Morgana', 'Neeko', 'Orianna',  'Taliyah', 'Zyra'],
"ESTJ": ['Aphelios', 'Caitlyn', 'Draven', 'Jinx', 'Kaisa', 'Kalista', 'Kindred', 'Lucian', 'MissFortune', 'Sivir', 'Xayah'],
"ISFP": ['Aatrox', 'Camille', 'Fiora', 'Irelia', 'Jax', 'LeeSin', 'Pantheon', 'Riven', 'Tryndamere', 'Vi', 'XinZhao', 'Yasuo', 'Yone'],
"ESFP": ['Bard', 'Ivern', 'Janna', 'Lulu', 'Nami', 'Sona', 'Soraka', 'Yuumi', 'Zilean'],
"INTJ": ['Akali', 'Fizz', 'Nocturne', 'Qiyana', 'Ahri', 'Jayce', 'Kassadin', 'Katarina', 'Diana', 'Leblanc', 'Talon', 'Zed', 'Pyke'],
"ENFP": ['Khazix', 'Shaco', 'Evelynn', 'Ekko', 'Nidalee', 'Rengar', 'Udyr', 'Kayn', 'MasterYi', 'Graves'],
"ISTJ": ['Quinn', 'Teemo', 'Tristana', 'Twitch', 'Vayne'],
"ENTP": ['Ezreal', 'Jhin', 'KogMaw', 'Varus', 'Kayle', 'Ashe', 'Senna', 'Corki'],
"INFJ": ['Elise', 'Swain', 'Fiddlesticks', 'Lissandra', 'Vladimir', 'Ryze', 'Sylas', 'Lillia', 'Rumble'],
"ESTP": ['Taric', 'Thresh', 'Blitzcrank', 'Rakan', 'Nautilus', 'Alistar', 'Leona', 'Braum']}

# mbti 조합 dict mbti : [좋은조합, 나쁜조합]
mbti_couple = {
    'entj': ['isfp', 'isfj'],
    'entp': ['isfj', 'isfp'],
    'intj': ['esfp', 'esfj'],
    'intp': ['esfj', 'esfp'],
    'estj': ['infp', 'infj'],
    'esfj': ['intp', 'intj'],
    'istj': ['enfp', 'enfj'],
    'isfj': ['entp', 'entj'],
    'enfj': ['istp', 'istj'],
    'enfp': ['istj', 'istp'],
    'infj': ['estp', 'estj'],
    'infp': ['estj', 'estp'],
    'estp': ['infj', 'infp'],
    'esfp': ['intj', 'intp'],
    'istp': ['enfj', 'enfp'],
    'isfp': ['entj', 'entp']
} 


result = []
with open('champions.json', 'r') as chams:

    cham_infos = json.load(chams)
    for cham_info in cham_infos:
        table = {}
        table["model"] = "demacia.champion"
        table["pk"] = cham_info["key"]
        cham_id = cham_info["key"]

        # 필드 채우기
        table["fields"] = {}
        table["fields"]["chamkey"] = cham_id
        table["fields"]["chamname"] = cham_info["id"]
        chamname = cham_info["id"]
        table["fields"]["chamtags"] = cham_info["tags"]
        # table["fields"]["partype"] = ''

        # 챔피언 딕셔너리
        if int(cham_id) in champ_dictionary:
            table["fields"]["dictionary"] = str(champ_dictionary[int(cham_id)])
        else:
            table["fields"]["dictionary"] = ''

        # 챔피언 라인
        table["fields"]["lane"] = cham_info["lane"]
        
        # 챔피언 mbti
        # cham_mbti = cham_info["?????"]
        # table["fields"]["mbti"] = cham_mbti
        for mbti, cham in MBTI.items():
            if chamname in cham:
                table["fields"]["mbti"] = mbti
                cham_mbti = mbti
                break

        # 챔피언별 승률
        if int(cham_id) in champion_winnig_rate:
            table["fields"]["winning_rate"] = str(champion_winnig_rate[int(cham_id)])
        else:
            table["fields"]["winning_rate"] = ''
        # 코드 불러온 후
        # table["fields"]["winningRate"] = champion_winnig_rate[cham_id]

        # mbti 좋은 조합
        # table["fields"]["goodmbti"] = '' 
        table["fields"]["goodmbti"] = mbti_couple[cham_mbti.lower()][0].upper()


        # mbti 나쁜 조합
        # table["fields"]["badmbti"] = '' 
        table["fields"]["badmbti"] = mbti_couple[cham_mbti.lower()][1].upper()

        # 이미지 src
        table["fields"]["imgsrc"] = "@/assets/img/tiles/" + str(chamname) + "_1.jpg"
        
        # 한글 이름
        table["fields"]["korname"] = ""

        result.append(table)

with open('cham.json', 'w', encoding="UTF-8") as make_file:
    json.dump(result, make_file, indent="\t")