# Dailyhunt Utilities



Fetch Dashboard

http://fdb-content.dailyhunt.in/NHFetch/getJobStackTrace.action

newshunt-root => nh-fetch => content-platform-commons => np-content-workflow => content-enrichments => ContentDashboard 

mvn -Dmaven.javadoc.skip=true -DskipTests clean install

***************************************************************************************************************

kibana

kibana.bat plugin --install elastic/sense

***************************************************************************************************************
http://192.168.10.25/NHFetch
admin/Eterno123

192.168.1.11:8080/NHFetch
admin/ETerno123
***************************************************************************************************************

project - topic classification => new platform, started prototyping for it.


platforms we have namely - 
1. current content platform 
2. new content platform 
3. enrichment service/platform.


setting up each of them in local, trying out their main functionality, familiarizing with DB schema, log structure, deployment etc.

***************************************************************************************************************
MYSQLDB

### Laptop =>

start => mysqld.exe

mysql -u root -p NEWSHUNT

mysql -u root -p MLDB < D:\verse\CMS\src\git-machine-learning-dashboard\machine-learning-dashboard\db-files\dev\db_schema.sql

shutdown => mysqladmin.exe -u root shutdown

### NEWSHUNT (master && slave DB) =>

connect to 192.168.10.25

sudo -u dh-content -i

master => mysql -h 192.168.1.25 -u newshunt -p NEWSHUNT -P 3306

slave => mysql -h 192.168.1.35 -u newshunt -p NEWSHUNT -P 3306

password : Q53zC2m

or 

master => use any of fetch servers, sudo webdoc and type sql (1.5, 1.11, 10.126, 10.159)

from 2.25 => mysql -h 192.168.1.35 -u nhuser -p NEWSHUNT -P 3306
password : Nh3#u$35@123!@#

mldb => mysql -h 192.168.24.84 -u mldbuser -p mldb -P 3306
password : MldB&$3%@123$

vocabulary => mysql -h 192.168.24.84 -u vocab_user -p vocabulary -P 3306
password : vocabulary

***************************************************************************************************************
mldb => 192.168.2.25 => 
ssh dh-tf-temp@192.168.2.25
password : nopassword

192.168.1.5 => 
mysql -A -h 192.168.1.25 -u newshunt -pQ53zC2m -DNEWSHUNT -e "Select cat.pk as cat_pk, cat.short_key as cat_short_key, cat.name as cat_name, cat.np_pk, np.name as np_name, np.short_key as np_short_key, lang.lang_eng as language from  e_category cat, e_newspaper np, e_lang_info lang where np.pk = cat.np_pk and lang.lang_index=np.language and lang.code = 'hi'" > out.csv

***************************************************************************************************************
24.131

mysql -udhcontent -peterno@123 -h 192.168.1.35 NEWSHUNT

or 

prod
mysql -h 192.168.1.35 -u dhcontent -p NEWSHUNT   => pwd - eterno@123

stage
mysql -udhcontent -peterno123 -h 192.168.2.101 NEWSHUNT

/mnt/vol1/dh-postgres/postgresql-10.3/bin/psql -U dh_readuser -h 192.168.132.68 -d dhpublish => pwd - dh@re!@#

mysql -udh-content -p134TYR29 -h 192.168.24.172 tv =? pwd - 134TYR29

***************************************************************************************************************

mvn package && java -jar target/gs-spring-boot-0.1.0.jar

java -jar dashboard-web\target\dashboard-web-1.0-SNAPSHOT.jar

java -jar -Dserver.port=8090 dashboard-web\target\dashboard-web-1.0-SNAPSHOT.jar


java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=6090,suspend=n -jar dashboard-web\target\dashboard-web-1.0-SNAPSHOT.jar
***************************************************************************************************************

long tableNo = (Math.abs(itemPk - 7500000) / 1000000) + 1;
***************************************************
*********************************************
Kafka installation (laptop)
*********************************************
https://dzone.com/articles/running-apache-kafka-on-windows-os


Run Zookeeper by opening a new cmd and type zkserver.

starting a Kafka server.

1. Go to your Kafka installation directory C:\kafka_2.11-0.9.0.0\
3. Now type ".\bin\windows\kafka-server-start.bat .\config\server.properties" and press Enter.

Console of another kafka server

kafka-console-consumer.bat --bootstrap-server 192.168.25.8:9092 --topic buzz_video_stage --from-beginning

*************************************************
Redis

http://192.168.7.72:3000/cis/ops/redis/cluster?host=192.168.3.63&port=7001

------------------------------------

Social & UGC Tasks
https://docs.google.com/spreadsheets/d/1FYr0jtz7eXgA4yMcJoUW_9H8k5o2HUD3zA5Do7Y9TUA/edit#gid=550951280
***************************************************************************
Sohanvir has prepared consolidated document for installation prerequisites and steps to run -
https://git.newshunt.com/p13n-core/obelix-notification-engine/blob/master/Readme.md
***********************************************************
git projects
https://git.newshunt.com/p13n-golang/contesting
https://git.newshunt.com/p13n-core/obelix-notification-engine
***********************************************************
Social & UGC APIs - Aug 19
https://docs.google.com/spreadsheets/d/1srDfszov1FTvuh_j6NBj-lFpI472tGMQnO9Bt4plPh4/edit#gid=2003885101

****************************

viper => logging

cobra => commandline arguments

gin => webserver

enginex => cache

****************************************

-------------------------------------------------------------------------------------------------
netstat -anp | grep "192.168.3.24"

netstat -anp | grep "192.168.3.24" | awk '{print $NF}' | sort | uniq -c

------------------------------------------------------------------------
mysqldump -umdm -pmeut2^7% -h dhvpc-stage-master-meta-mysql-n1.dailyhunt.in qa_mdm_new_02_01_20 | mysql -umdm -pmeut2^7% -h dhvpc-stage-master-meta-mysql-n1.dailyhunt.in mdm_image


mysqldump -umdm -pmeut2^7% -h dhvpc-stage-master-meta-mysql-n1.dailyhunt.in qa_mdm_new_02_01_20 | mysql -umdm -pmeut2^7% -h dhvpc-stage-master-meta-mysql-n1.dailyhunt.in mdm_image


mysql -udh-mdm-service -pmdmp$%D -h dh4-a1-prod-mdm-mysql-n1.dailyhunt.in mdm_perf


mysqldump -udh-mdm-service -pmdmp$%D -h dh4-a1-prod-mdm-mysql-n1.dailyhunt.in mdm_prod_migration_1 | mysql -udh-mdm-service -pmdmp$%D -h dh4-a1-prod-mdm-mysql-n1.dailyhunt.in mdm_prod_perf

***********************************************************


http://127.0.0.1:8000/api/v1/entity/getStoriesMeta?entityId=13d19aadf24bc96f1a002012e040c552&entityType=HASHTAG

tail -f mdm.log | grep "\[GIN\]"|egegrep "SOURCE"|egrep -v "ms|Âµs"


tail -f mdm.log | grep "\[GIN\]"|egrep -v "ms|Âµs"




grep "\[GIN\]" mdm.log |egrep "SOURCE"|egrep -v "ms|Âµs"


grep "\[GIN\]" mdm.log |egrep "SOURCE"|egrep -v "Âµs"


grep "\[GIN\]" mdm.log |egrep -v "ms|Âµs"

 

 zgrep "\[GIN\]" mdm-2020-03-15T17-02-33.966.log.gz |egrep "SOURCE"|egrep -v "ms|Âµs"

  zgrep "\[GIN\]" mdm-2020-03-15T* |egrep "SOURCE"|egrep -v "ms|Âµs"

  

zgrep "\[GIN\]" mdm-2020-03-15T17-02-33.966.log.gz |egrep -v "ms|Âµs" 



------

2 archives
events archive and orc archive
http://192.168.3.167:50070/explorer.html#/
******************************************************************
events archive
******************************************************************
Camus events - /camus/topics/analytics_news_processed_events/hourly/2018/09  => 
hdfs archive - /old_data_events/analytics_news_processed_events/2018/09/22/00 =>
NFS archive - /old_events_archival/cloudera_hdfs_data/old_data_events/analytics_news_processed_events

******************************************************************
ORC archive
******************************************************************
Camus events - /camus/topics/analytics_news_processed_events/hourly/2018/09  => 
Deduped events - /events/deduped-events/hourly/2018/09 =>
orc events compressed backup - /orc-hive-staging/app_notification_delivered/properties_et_year=2018  =>
NFS archive - /old_events_archival/archived_orc_data/orc-hive-staging/
******************************************************************
******************************************************************
ORC archive & buffer
******************************************************************
ssh 192.168.3.183 (login from 3.177-hdfs)
*********************************
ORC copy & checksum scripts
******************************************************************
ls ~/scripts/ (from 3.183)

script1 - copy files from hdfs to buffer.
script2 - evaluate checksum for 100 random files for each day.
******************************************************************
ORC hive backup script
******************************************************************
less /home/hdfs/orc-archival-process/archive-orc-data-wrapper.sh
less /home/hdfs/orc-archival-process/archive-orc-data.sh
nohup sh archive-orc-data.sh 2018-03-24 & 
******************************************************************
******************************************************************
ORC schema-generate
******************************************************************
schema - /home/hdfs/hive-loader/hourly-hive-schema-generate-wrapper.sh
log - /grid/2/tmp/hdfs-run-logs/hive-schema-generate.log.2018-09-19-12
******************************************************************
Things to do
******************************************************************
Raw Events in hdfs - 2 months (from july 26 onwards) - keep only till last 15 days.
Orc hive data in hdfs - 6 months - will increase to 1 year

******************************************************************
******************************************************************
ORC Table data view
******************************************************************
view data - hive --orcfiledump -d /orc-hive-staging/app_card_toggled/properties_et_year=2018/properties_et_month=9/properties_et_day=20/app_card_toggled_ph@2018-09-20-00-0_ct@4857642249262140
view schema - hive --orcfiledump  /orc-hive-staging/app_card_toggled/properties_et_year=2018/properties_et_month=9/properties_et_day=20/app_card_toggled_ph@2018-09-20-00-0_ct@4857642249262140

******************************************************************
DSR Report
******************************************************************
URL - http://192.168.3.177:8088/dailyhunt/cronstats/hourly/today
doc : https://docs.google.com/document/d/1hAsx-E6WcQ2SkH-XbBtcRFxsUgkIFKSqleA8GehJDMA/edit?ts=5ba4c458#
*********************************
Job1 - UUP Daily Merge Job 
*********************************
Job Name : ProfileAggregateMergeRunner-USER-MONTH_YEAR_CUMULATIVE-2018-09-21
Run Time : 2:15 AM.
Expected Run Duration: 2 Hr
*********************************
Job2 - Daily Monthly CF (column family) export job 
*********************************
Job Name: uup-daily-monthly-export
Run Time : 4:15 AM
Expected Run Duration: 45 min.
*********************************
Job3 - Script(query) to collect DAU and MAU 
*********************************

*********************************
Job4 - Daily pre-aggregate merge job
*********************************
2 Flink jobs run sequentially
DailyPreAggregatesMerge-DAY-2018-09-21
DailyPreAggregatesMerge-MONTHTILLDATE-2018-09-21

*********************************
Job5 - Report send job
*********************************
2 Flink jobs run sequentially
DailyPreAggregatesMerge-DAY-2018-09-21
DailyPreAggregatesMerge-MONTHTILLDATE-2018-09-21

need to check -  less /home/hdfs/profile-update/daily-app_release_from_uup_wrapper.sh
rerun daily and monthly (dau and mau) if an exception





CE changes

**********************************
Support embedded link in hbase
detailed_assets.embedded_assets
All the entries in that array with embedded_asset_type = LINK
**********************************

then Swapnil has written has written a separate consumer and in the same class she as written all the persistene
she can use the same kafka topic and her enrichment as separate enrichment class
and use persistence layer i have already written for l2
I mean she can add her logic in a separate executor

**********************************
hashtag id and topic id wil be same first when u create topic

if primary hashtag change no change in topic id only primary hashtg id changes

followCount can be @hashta level and also @topic level.
---------------------------------------------

app id is part of req from client

appid mapping is reqd

where the content will go decide by CIS/RECO based config (appId , sourceId)

appId level publish and moderation status  reqd=> can we have separate schema for each APP??

deeplink changes
- needs to inlcude appid

content side hashtag deeplink
- needs to change @runtime (NBE) based on appId.

********************

hashtag is common across app

deeplink url will be different based on appId

***********************

appId changes in all tables

need to know which and all topics / hashtags we need to support first

source side changes => need to get new URL structure to pass appId.

deeplink url => deeplink structure can change based on appId.

Needs to add appId validation in all APIs. => appId cannot be null.

*************************

1)come up with list of topics for DH Share app - (a) default pages (b) in + section.

2) this needs to be configured as static json in MDM as we discussed

3) we would like to setup DH Share / Social version of DH app, which would only show Viral and Video content.
Even on frontend we would want to use same skin and codebase as DH, to start with. While brand name, and logo etc would be different.

Algo: Viral and Video UGC content, ICC content, OGC content would win on merit and virality. UGC content would not be limited to followers.

Client Id: for all practical purposes we would assign same client id to the user as on DH, if user already has DH application. While internal data stores would be namespaced by app id to distinguish from which app the data is.

Follows / Activity: this would be namespaced to the app.

find out how the behaviour is diff for hashtag and location (like bglr as a location and hashtag)
----------------------------------------------------
1) if there is only one content then dont show all.

2) need auto child with other topic childs (to support web item topics).

3) how abt supporting def child in 15.xx

4) automatic increment of topic id for user generated hashtag

**************************


MDM meta changes to support hashtag and topics

Add date to hashtag and topic (create time)

and densityCount (start & update)

********************************

Hi Shanmugam,
Yes, generally for any topic there will be only one fetchMode. Here it happened because this topic got both webitem and dynamic properties. For webitem and dynamic topics, data-fetch details are different and both are saved in different places. For this topic both were present

*********************

CE retry logic for buzz video 
2020-05-14 23:53:37.310 [http-nio-9999-exec-2] [ERROR] c.n.buzz.enrichment.evaluator.BuzzSpeechEvaluator [BuzzSpeechEvaluator.java:135] - Error in BuzzSpeechEvaluator for itemId :: b20532659 
::: 
org.json.JSONException: JSONObject["text"] not a string.
        at org.json.JSONObject.getString(JSONObject.java:721)
        at com.newshunt.buzz.enrichment.evaluator.BuzzSpeechEvaluator.evaluate(BuzzSpeechEvaluator.java:87)
        at com.newshunt.post.enrichment.evaluator.PostItemParentEvaluator.evaluate(PostItemParentEvaluator.java:19)
        at com.newshunt.post.enrichment.processor.ObelixPostItemProcessor.process(ObelixPostItemProcessor.java:17)
		

---------------------------------------------

Hindi 

15K + 1K (ICC)

16K => (0.6)
---------------------------------------------





