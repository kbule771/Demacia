from .models import Champion, Videopost , Match, Replylikes , Videopostlikes, User
from rest_framework import serializers, viewsets
# Seriallizer는 API를 통한 요청에 대한 응답의 형태를을 결정해주는 클래스이다. 
# ViewSet은 요청을 처리하여 응답을 해주는 클래스이다. 
# 이곳에 get, post, put, patch, delete에 대한 액션을 지정

class ChampionSerializer(serializers.ModelSerializer):
    class Meta:
        model = Champion
        fields = '__all__'

class ChampionDictionarySerializer(serializers.ModelSerializer):
    class Meta:
        model = Champion
        fields = ['dictionary']

class ChampionViewSet(viewsets.ModelViewSet):
    queryset = Champion.objects.all()
    serializer_class = ChampionSerializer

class VideopostSerializer(serializers.ModelSerializer):
    class Meta:
        model = Videopost
        fields = '__all__'    

class VideopostViewSet(viewsets.ModelViewSet):
    queryset = Videopost.objects.all()
    serializer_class =  VideopostSerializer
 
class MatchSerializer(serializers.ModelSerializer):
    class Meta:
        model = Match
        fields = '__all__'  

class MatchViewSet(viewsets.ModelViewSet):
    queryset = Match.objects.all()
    serializer_class =  MatchSerializer

class ReplylikesSerializer(serializers.ModelSerializer):
    class Meta:
        model = Replylikes
        fields = '__all__'   

class ReplylikesViewSet(viewsets.ModelViewSet):
    queryset = Replylikes.objects.all()
    serializer_class =  ReplylikesSerializer

class VideopostlikesSerializer(serializers.ModelSerializer):
    class Meta:
        model = Videopostlikes
        fields = '__all__'   

class VideopostlikesViewSet(viewsets.ModelViewSet):
    queryset = Videopostlikes.objects.all()
    serializer_class =  VideopostlikesSerializer


class UserSerializer(serializers.ModelSerializer):

    # def update(self, instance, validated_data):
    #     instance.userno = validated_data.get('userno', instance.userno)
    #     return instance

    class Meta:
        model = User
        fields = ['userno', 'mbti']
