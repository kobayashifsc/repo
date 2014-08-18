echo off
echo ---------------------------------
echo ドラッグしたファイル： %1
echo ---------------------------------
java -cp ArchiveFileRemarke.jar batch.main.UnzipRAR %1
pause
