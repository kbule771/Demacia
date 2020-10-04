import json # import json module

with open('champion_stats.json') as json_file:
    json_data = json.load(json_file)

    # clustter1 = {}
    # for i in range(len(json_data)):
    #     if json_data[i]['tags'] in clustter1:
    #         clustter1[json_data[i]['tags']].append(json_data[i]['id'] )
    #     else:
    #         clustter1[json_data[i]['tags']] = [json_data[i]['id']]

    # sorted_clusster = sorted(clustter1.values(),key= lambda x:len(x),reverse=True)
    # print(sorted_clusster)

# [['Aatrox', 'Camille', 'Darius', 'DrMundo', 'Garen', 'Gnar', 'Hecarim', 'Illaoi', 'Kled', 'MonkeyKing', 'Nasus', 'Olaf', 'Renekton', 'Sett', 'Shyvana', 'Skarner', 'Trundle', 'Udyr', 'Urgot', 'Volibear', 'Warwick', 'Yorick'],
# ['Annie', 'AurelionSol', 'Brand', 'Cassiopeia', 'Karthus', 'Lissandra', 'TwistedFate', 'Veigar', 'Velkoz', 'Viktor', 'Vladimir', 'Xerath', 'Ziggs'],
# ['Aphelios', 'Caitlyn', 'Corki', 'Draven', 'Graves', 'Jinx', 'Kaisa', 'Kalista', 'Kindred', 'Lucian', 'MissFortune', 'Sivir', 'Xayah'],
# ['Anivia', 'Fiddlesticks', 'Heimerdinger', 'Karma', 'Lux', 'Morgana', 'Neeko', 'Orianna', 'Syndra', 'Taliyah', 'Zoe', 'Zyra'],
# ['Blitzcrank', 'JarvanIV', 'Malphite', 'Nautilus', 'Nunu', 'Ornn', 'Poppy', 'Rammus', 'Sejuani', 'Singed', 'Sion', 'Zac'],
# ['Fiora', 'Irelia', 'Jax', 'Kayn', 'LeeSin', 'Pantheon', 'Riven', 'Tryndamere', 'Vi', 'XinZhao', 'Yasuo'],
# ['Bard', 'Ivern', 'Janna', 'Lulu', 'Nami', 'Sona', 'Soraka', 'Yuumi', 'Zilean'],
# ['Ekko', 'Fizz', 'MasterYi', 'Nocturne', 'Qiyana', 'Rengar', 'Yone'],
# ['Akali', 'Khazix', 'Shaco', 'Talon', 'Zed'],
# ['Evelynn', 'Kassadin', 'Katarina', 'Leblanc', 'Nidalee'],
# ['Quinn', 'Teemo', 'Tristana', 'Twitch', 'Vayne'],
# ['Amumu', 'Chogath', 'Galio', 'Maokai'],
# ['Diana', 'Gragas', 'Lillia', 'Rumble'],
# ['Ezreal', 'Jhin', 'KogMaw', 'Varus'],
# ['Ahri', 'Malzahar', 'Sylas'],
# ['Elise', 'Ryze', 'Swain'],
# ['Gangplank', 'Mordekaiser', 'RekSai'],
# ['Alistar', 'Leona'], 
# ['Ashe', 'Senna'],
# ['Azir', 'Kennen'],
# ['Braum', 'TahmKench'],
# ['Taric', 'Thresh'],
# ['Jayce'],
# ['Kayle'],
# ['Pyke'],
# ['Rakan'],
# ['Shen']]
    # clustter2 = {}
    # for i in range(len(json_data)):
    #     if json_data[i]['lane'] in clustter1:
    #         clustter2[json_data[i]['lane']].append(json_data[i]['id'] )
    #     else:
    #         clustter2[json_data[i]['lane']] = [json_data[i]['id']]
    
    # sorted_clusster = sorted(clustter2.items(),key= lambda x:len(x),reverse=True)
    # print(sorted_clusster)
# [("['jungle']", ['Amumu', 'Elise', 'Evelynn', 'Fiddlesticks', 'Graves', 'Ivern', 'JarvanIV', 'Karthus', 'Kayn', 'Khazix', 'Kindred', 'LeeSin', 'Lillia', 'MasterYi', 'Nidalee', 'Rammus', 'RekSai', 'Sejuani', 'Skarner', 'Taliyah', 'Trundle', 'Udyr', 'Vi', 'XinZhao']),
# ("['mid', 'support']", ['Annie', 'Galio', 'Neeko']), ("['ad']", ['Aphelios', 'Ashe', 'Caitlyn', 'Draven', 'Ezreal', 'Jhin', 'Jinx', 'Kaisa', 'Kalista', 'KogMaw', 'MissFortune', 'Sivir', 'Tristana', 'Twitch', 'Varus', 'Vayne', 'Xayah']),
# ("['mid']", ['Ahri', 'Anivia', 'AurelionSol', 'Azir', 'Fizz', 'Kassadin', 'Katarina', 'Leblanc', 'Lissandra', 'Malzahar', 'Orianna', 'Qiyana', 'Syndra', 'Talon', 'TwistedFate', 'Veigar', 'Viktor', 'Zed', 'Zoe']),
# ("['support']", ['Alistar', 'Bard', 'Blitzcrank', 'Braum', 'Janna', 'Karma', 'Leona', 'Morgana', 'Nami', 'Nautilus', 'Rakan', 'Sona', 'Soraka', 'Swain', 'Taric', 'Thresh', 'Yuumi', 'Zilean', 'Zyra']),
# ("['top']", ['Darius', 'Fiora', 'Gangplank', 'Garen', 'Gnar', 'Heimerdinger', 'Illaoi', 'Kennen', 'Kled', 'Nasus', 'Quinn', 'Riven', 'Singed', 'Sion', 'Teemo', 'Tryndamere', 'Urgot', 'Yorick']),
# ("['top', 'mid']", ['Aatrox', 'Akali', 'Camille', 'Jayce', 'Kayle', 'Mordekaiser', 'Ornn', 'Renekton']),
# ("['support', 'mid']", ['Brand', 'Lux', 'Pantheon', 'Pyke', 'Velkoz', 'Xerath']),
# ("['top', 'jungle']", ['DrMundo', 'Jax', 'MonkeyKing', 'Rengar']),
# ("['jungle', 'top']", ['Hecarim', 'Olaf', 'Volibear', 'Warwick']),
# ("['mid', 'top']", ['Diana', 'Rumble', 'Ryze', 'Yone']),
# ("['jungle', 'mid']", ['Gragas', 'Nocturne', 'Nunu']),
# ("['support', 'top']", ['Lulu', 'TahmKench']),
# ("['mid', 'ad']", ['Cassiopeia', 'Corki']),
# ("['top', 'support']", ['Maokai', 'Shen']),
# ("['mid', 'top', 'jungle']", ['Chogath']),
# ("['mid', 'jungle']", ['Ekko']),
# ("['top',  'mid']", ['Irelia']),
# ("['ad', 'mid', 'top']", ['Lucian']),
# ("['top', 'mid', 'support']", ['Malphite']),
# ("['top', 'jungle', 'support']", ['Poppy']),
# ("['ad', 'support']", ['Senna']),
# ("['support', 'top', 'jungle']", ['Sett']),
# ("['jungle', 'support']", ['Shaco']),
# ("['jugnle']", ['Shyvana']),
# ("['mid', 'jungle', 'top']", ['Sylas']),
# ("['top' ,'mid']", ['Vladimir']),
#  ("['mid', 'ad', 'top']", ['Yasuo']),
#  ("['jungle', 'top', 'support']", ['Zac']),
# ("['ad', 'mid']", ['Ziggs'])]

    # dict_mbtichampion ={"['탱 마법사']": ['Amumu', 'Chogath', 'Galio', 'Volibear', 'Mordekaiser', 'Zac', 'Gangplank' ],
    # "['극탱']": ['Shen', 'Malphite', 'Sion', 'Maokai', 'Nasus', 'Ornn', 'Poppy', 'Singed', 'TahmKench', 'Urgot', 'Sett', 'DrMundo' ],
    # "['탑딜탱']": [ 'Darius', 'Gnar', 'MonkeyKing', 'Garen', 'Olaf', 'Renekton', 'Warwick', 'Yorick',  'Illaoi', 'Kled', 'Kennen'],
    # "['정글탱]": [ 'JarvanIV', 'Hecarim', 'Skarner', 'Trundle', 'Shyvana', 'RekSai', 'Rammus', 'Nunu', 'Sejuani', 'Gragas'],
    # "['극딜 마법사']": [ 'Syndra', 'Veigar', 'Zoe', 'Velkoz', 'Viktor', 'Xerath', 'Ziggs', 'Azir', 'Malzahar'],
    # "['지속딜 마법사']": [ 'Anivia', 'Heimerdinger', 'AurelionSol', 'Cassiopeia', 'Karthus', 'TwistedFate',],
    # "['서포터 마법사']": ['Annie',  'Brand', 'Karma', 'Lux', 'Morgana', 'Neeko', 'Orianna',  'Taliyah', 'Zyra'],
    # "['원딜']": ['Aphelios', 'Caitlyn', 'Draven', 'Jinx', 'Kaisa', 'Kalista', 'Kindred', 'Lucian', 'MissFortune', 'Sivir', 'Xayah'],
    # "['근접딜']": ['Aatrox', 'Camille', 'Fiora', 'Irelia', 'Jax', 'LeeSin', 'Pantheon', 'Riven', 'Tryndamere', 'Vi', 'XinZhao', 'Yasuo', 'Yone'],
    # "['마법유틸서포터']": ['Bard', 'Ivern', 'Janna', 'Lulu', 'Nami', 'Sona', 'Soraka', 'Yuumi', 'Zilean'],
    # "['미드암살자']": ['Akali', 'Fizz', 'Nocturne', 'Qiyana', 'Ahri', 'Jayce', 'Kassadin', 'Katarina', 'Diana', 'Leblanc', 'Talon', 'Zed', 'Pyke'],
    # "['정글암살자']": ['Khazix', 'Shaco', 'Evelynn', 'Ekko', 'Nidalee', 'Rengar', 'Udyr', 'Kayn', 'MasterYi', 'Graves'],
    # "['원거리암살자']": ['Quinn', 'Teemo', 'Tristana', 'Twitch', 'Vayne'],
    # "['원딜 마법사']": ['Ezreal', 'Jhin', 'KogMaw', 'Varus', 'Kayle', 'Ashe', 'Senna', 'Corki'],
    # "['근접 마법사']": ['Elise', 'Swain', 'Fiddlesticks', 'Lissandra', 'Vladimir', 'Ryze', 'Sylas', 'Lillia', 'Rumble'],
    # "['탱서포터']": ['Taric', 'Thresh', 'Blitzcrank', 'Rakan', 'Nautilus', 'Alistar', 'Leona', 'Braum']}
    
    # mbti_championlist = list(dict_mbtichampion.values())
    # cnt = 0
    # for i in range(len(mbti_championlist)):
    #     cnt += len(mbti_championlist[i])
    # print(cnt)
    MBTI = {"['ENTJ']": ['Amumu', 'Chogath', 'Galio', 'Volibear', 'Mordekaiser', 'Zac', 'Gangplank' ],
"['INFP']": ['Shen', 'Malphite', 'Sion', 'Maokai', 'Nasus', 'Ornn', 'Poppy', 'Singed', 'TahmKench', 'Urgot', 'Sett', 'DrMundo' ],
"['INTP']": [ 'Darius', 'Gnar', 'MonkeyKing', 'Garen', 'Olaf', 'Renekton', 'Warwick', 'Yorick',  'Illaoi', 'Kled', 'Kennen'],
"['ENFJ']": [ 'JarvanIV', 'Hecarim', 'Skarner', 'Trundle', 'Shyvana', 'RekSai', 'Rammus', 'Nunu', 'Sejuani', 'Gragas'],
"['ISFJ']": [ 'Syndra', 'Veigar', 'Zoe', 'Velkoz', 'Viktor', 'Xerath', 'Ziggs', 'Azir', 'Malzahar'],
"['ISTP']": [ 'Anivia', 'Heimerdinger', 'AurelionSol', 'Cassiopeia', 'Karthus', 'TwistedFate',],
"['ESFJ']": ['Annie',  'Brand', 'Karma', 'Lux', 'Morgana', 'Neeko', 'Orianna',  'Taliyah', 'Zyra'],
"['ESTJ']": ['Aphelios', 'Caitlyn', 'Draven', 'Jinx', 'Kaisa', 'Kalista', 'Kindred', 'Lucian', 'MissFortune', 'Sivir', 'Xayah'],
"['ISFP']": ['Aatrox', 'Camille', 'Fiora', 'Irelia', 'Jax', 'LeeSin', 'Pantheon', 'Riven', 'Tryndamere', 'Vi', 'XinZhao', 'Yasuo', 'Yone'],
"['ESFP']": ['Bard', 'Ivern', 'Janna', 'Lulu', 'Nami', 'Sona', 'Soraka', 'Yuumi', 'Zilean'],
"['INTJ']": ['Akali', 'Fizz', 'Nocturne', 'Qiyana', 'Ahri', 'Jayce', 'Kassadin', 'Katarina', 'Diana', 'Leblanc', 'Talon', 'Zed', 'Pyke'],
"['ENFP']": ['Khazix', 'Shaco', 'Evelynn', 'Ekko', 'Nidalee', 'Rengar', 'Udyr', 'Kayn', 'MasterYi', 'Graves'],
"['ISTJ']": ['Quinn', 'Teemo', 'Tristana', 'Twitch', 'Vayne'],
"['ENTP']": ['Ezreal', 'Jhin', 'KogMaw', 'Varus', 'Kayle', 'Ashe', 'Senna', 'Corki'],
"['INFJ']": ['Elise', 'Swain', 'Fiddlesticks', 'Lissandra', 'Vladimir', 'Ryze', 'Sylas', 'Lillia', 'Rumble'],
"['ESTP']": ['Taric', 'Thresh', 'Blitzcrank', 'Rakan', 'Nautilus', 'Alistar', 'Leona', 'Braum']}
    print(MBTI)
    MBTI = list(MBTI.values())
    cnt = 0
    for i in range(len(MBTI)):
        cnt += len(MBTI[i])
    print(cnt)