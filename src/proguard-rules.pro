# Add any ProGuard configurations specific to this
# extension here.

-keep public class me.aemo.simplebar.SimpleBar {
    public *;
 }
-keeppackagenames gnu.kawa**, gnu.expr**

-optimizationpasses 4
-allowaccessmodification
-mergeinterfacesaggressively

-repackageclasses 'me/aemo/simplebar/repack'
-flattenpackagehierarchy
-dontpreverify
