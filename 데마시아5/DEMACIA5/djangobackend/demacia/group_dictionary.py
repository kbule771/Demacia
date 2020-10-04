from ..chamdata import champion_group_2_array
import numpy
champ_dictionary = dict()


for i in range(877):
    if i not in champ_dictionary and sum(champion_group_2_array[i]) > 0 :
        champ_dictionary[i] = list(numpy.argsort(champion_group_2_array[i])[::-1][:4])


print(champ_dictionary)
