@echo off
@echo January-31 February-28 March-31 April-30 May-31 June-30 July-31 August -31 September-30 October-31 November-30 December-31
:start
set /a var+=1
 
echo a >> test.txt
git commit --date=" April %var% 9:05:20 2016 +0800" -am "turns fucking green"
echo a >> test.txt
git commit --date="  April %var% 9:06:21 2016 +0800" -am "turns fucking green"
 echo a >> test.txt
git commit --date=" April %var% 9:08:24 2016 +0800" -am "turns fucking green"
  

if %var% leq 30 GOTO start

git push origin master
@echo Mission finished !
