echo off
echo ---------------------------------
echo ドラッグしたディレクトリ： %1
echo ---------------------------------
java -cp ArchiveFileRemarke.jar batch.main.ZipDirectory %1
pause
