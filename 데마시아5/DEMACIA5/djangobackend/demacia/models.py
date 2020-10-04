# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey and OneToOneField has `on_delete` set to the desired behavior
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models


class AuthGroup(models.Model):
    name = models.CharField(unique=True, max_length=150)

    class Meta:
        managed = False
        db_table = 'auth_group'


class AuthGroupPermissions(models.Model):
    group = models.ForeignKey(AuthGroup, models.DO_NOTHING)
    permission = models.ForeignKey('AuthPermission', models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'auth_group_permissions'
        unique_together = (('group', 'permission'),)


class AuthPermission(models.Model):
    name = models.CharField(max_length=255)
    content_type = models.ForeignKey('DjangoContentType', models.DO_NOTHING)
    codename = models.CharField(max_length=100)

    class Meta:
        managed = False
        db_table = 'auth_permission'
        unique_together = (('content_type', 'codename'),)


class AuthUser(models.Model):
    password = models.CharField(max_length=128)
    last_login = models.DateTimeField(blank=True, null=True)
    is_superuser = models.IntegerField()
    username = models.CharField(unique=True, max_length=150)
    first_name = models.CharField(max_length=150)
    last_name = models.CharField(max_length=150)
    email = models.CharField(max_length=254)
    is_staff = models.IntegerField()
    is_active = models.IntegerField()
    date_joined = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'auth_user'


class AuthUserGroups(models.Model):
    user = models.ForeignKey(AuthUser, models.DO_NOTHING)
    group = models.ForeignKey(AuthGroup, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'auth_user_groups'
        unique_together = (('user', 'group'),)


class AuthUserUserPermissions(models.Model):
    user = models.ForeignKey(AuthUser, models.DO_NOTHING)
    permission = models.ForeignKey(AuthPermission, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'auth_user_user_permissions'
        unique_together = (('user', 'permission'),)


class Champion(models.Model):
    chamkey = models.IntegerField()
    chamname = models.CharField(max_length=100)
    chamtags = models.CharField(max_length=200)
    dictionary = models.CharField(max_length=200)
    lane = models.CharField(max_length=200)
    mbti = models.CharField(max_length=50)
    winning_rate = models.FloatField()
    goodmbti = models.CharField(max_length=50)
    badmbti = models.CharField(max_length=50)
    imgsrc = models.CharField(max_length=200, blank=True, null=True)
    korname = models.CharField(max_length=200, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'champion'


class DjangoAdminLog(models.Model):
    action_time = models.DateTimeField()
    object_id = models.TextField(blank=True, null=True)
    object_repr = models.CharField(max_length=200)
    action_flag = models.PositiveSmallIntegerField()
    change_message = models.TextField()
    content_type = models.ForeignKey('DjangoContentType', models.DO_NOTHING, blank=True, null=True)
    user = models.ForeignKey(AuthUser, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'django_admin_log'


class DjangoContentType(models.Model):
    app_label = models.CharField(max_length=100)
    model = models.CharField(max_length=100)

    class Meta:
        managed = False
        db_table = 'django_content_type'
        unique_together = (('app_label', 'model'),)


class DjangoMigrations(models.Model):
    app = models.CharField(max_length=255)
    name = models.CharField(max_length=255)
    applied = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'django_migrations'


class DjangoSession(models.Model):
    session_key = models.CharField(primary_key=True, max_length=40)
    session_data = models.TextField()
    expire_date = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'django_session'


class Likes(models.Model):
    likeno = models.AutoField(primary_key=True)
    postno = models.ForeignKey('Post', models.DO_NOTHING, db_column='postno')
    userno = models.ForeignKey('User', models.DO_NOTHING, db_column='userno')

    class Meta:
        managed = False
        db_table = 'likes'


class Match(models.Model):
    matchno = models.AutoField(primary_key=True)
    recommand_champion = models.CharField(max_length=200)
    lane = models.CharField(max_length=100)
    userno = models.ForeignKey('User', models.DO_NOTHING, db_column='userno')

    class Meta:
        managed = False
        db_table = 'match'


class Post(models.Model):
    postno = models.AutoField(primary_key=True)
    userno = models.ForeignKey('User', models.DO_NOTHING, db_column='userno')
    postdate = models.DateTimeField()
    view = models.IntegerField(blank=True, null=True)
    title = models.CharField(max_length=256)
    content = models.TextField()
    video = models.CharField(max_length=256, blank=True, null=True)
    thumbnail = models.CharField(max_length=256, blank=True, null=True)
    option1 = models.CharField(max_length=50)
    option2 = models.CharField(max_length=50)
    deadline = models.DateTimeField()
    mileage = models.PositiveIntegerField()
    isfinished = models.IntegerField(db_column='isFinished', blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'post'


class Reply(models.Model):
    replyno = models.AutoField(primary_key=True)
    postno = models.ForeignKey(Post, models.DO_NOTHING, db_column='postno')
    userno = models.ForeignKey('User', models.DO_NOTHING, db_column='userno')
    content = models.TextField()
    replydate = models.DateTimeField(blank=True, null=True)
    replyopt = models.IntegerField(db_column='replyOpt')  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'reply'


class Replylikes(models.Model):
    replikeno = models.AutoField(primary_key=True)
    replyno = models.ForeignKey(Reply, models.DO_NOTHING, db_column='replyno')
    userno = models.ForeignKey('User', models.DO_NOTHING, db_column='userno')

    class Meta:
        managed = False
        db_table = 'replylikes'


class User(models.Model):
    userno = models.AutoField(primary_key=True)
    useremail = models.CharField(unique=True, max_length=128)
    userpw = models.CharField(max_length=128, blank=True, null=True)
    usernickname = models.CharField(max_length=128)
    userimage = models.CharField(max_length=256, blank=True, null=True)
    usercreatedate = models.DateTimeField(blank=True, null=True)
    usersolranknum = models.IntegerField(db_column='userSolRankNum', blank=True, null=True)  # Field name made lowercase.
    usergameranknum = models.IntegerField(db_column='userGameRankNum', blank=True, null=True)  # Field name made lowercase.
    usersoltierstr = models.CharField(db_column='userSolTierStr', max_length=128, blank=True, null=True)  # Field name made lowercase.
    usersolrankstr = models.CharField(db_column='userSolRankStr', max_length=128, blank=True, null=True)  # Field name made lowercase.
    usergametierstr = models.CharField(db_column='userGameTierStr', max_length=128, blank=True, null=True)  # Field name made lowercase.
    usergamerankstr = models.CharField(db_column='userGameRankStr', max_length=128, blank=True, null=True)  # Field name made lowercase.
    summonername = models.CharField(db_column='summonerName', max_length=255, blank=True, null=True)  # Field name made lowercase.
    usermileage = models.IntegerField(db_column='userMileage', blank=True, null=True)  # Field name made lowercase.
    providername = models.CharField(db_column='providerName', max_length=128, blank=True, null=True)  # Field name made lowercase.
    accesstoken = models.CharField(max_length=128, blank=True, null=True)
    accountid = models.CharField(max_length=128, blank=True, null=True)
    mbti = models.CharField(max_length=128, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'user'


class Videopost(models.Model):
    videopostno = models.IntegerField(primary_key=True)
    userno = models.ForeignKey(User, models.DO_NOTHING, db_column='userno')
    video = models.CharField(max_length=250)
    thumbnail = models.CharField(max_length=250, blank=True, null=True)
    data = models.CharField(max_length=500, blank=True, null=True)
    isprivate = models.IntegerField()
    view = models.IntegerField(blank=True, null=True)
    postdate = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'videopost'



class Videopostlikes(models.Model):
    vpostlikeno = models.AutoField(primary_key=True)
    userno = models.ForeignKey(User, models.DO_NOTHING, db_column='userno')
    videopostno = models.ForeignKey(Videopost, models.DO_NOTHING, db_column='videopostno')

    class Meta:
        managed = False
        db_table = 'videopostlikes'


class Vote(models.Model):
    voteno = models.AutoField(primary_key=True)
    postno = models.ForeignKey(Post, models.DO_NOTHING, db_column='postno')
    userno = models.ForeignKey(User, models.DO_NOTHING, db_column='userno')
    usergameranknum = models.IntegerField()
    usersolranknum = models.IntegerField()
    value = models.IntegerField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'vote'
