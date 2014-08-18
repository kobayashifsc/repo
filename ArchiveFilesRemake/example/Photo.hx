import flash.filters.DropShadowFilter;
import flash.filters.BitmapFilter;
import flash.geom.Matrix;
import flash.MovieClip;

class Photo{
    var res_name:String;
    var borderShadow:DropShadowFilter;
    var photoShadow:DropShadowFilter;
    var transformMatrix:Matrix;
    var parent:MovieClip;
    var self:MovieClip;
    var note:String;

    public function getResourceName():String {
        return res_name;
    }
    
    public function new(res_name:String, left:Int, top:Int, note:String, parent:MovieClip) {
        this.res_name = res_name;
        this.note = note;
        
        borderShadow = new DropShadowFilter(4, 45, 0x000000, 0.6, 6, 6, 2, 4);

        photoShadow = new DropShadowFilter(4, 100, 0x000000, 0.6, 4, 4, 2, 4, true);
        
        this.transformMatrix = new Matrix();
        var angle =  - 17 + Math.floor(Math.random() * 36);
        var rotation:Float = 2 * Math.PI * (angle / 360);
        transformMatrix.rotate(rotation);
        transformMatrix.translate(left, top);
        
        this.parent = parent;

    }
    
    public function draw(depth:Int):Void {
          
        var tmpMc = cast(parent.createEmptyMovieClip(res_name, depth), MovieClip);

        var gr = cast(tmpMc.attachMovie(res_name, "gr", 4), MovieClip);
          
        var photoFilters = new Array<BitmapFilter>();
        photoFilters.push(photoShadow);
        gr.filters = photoFilters;

        var w = gr._width;
        var h = gr._height;
        
        var w_scale = 280/ w * .7;
        var h_scale = 300/ h * .7;
        
        var scale = Math.min(w_scale, h_scale);
        transformMatrix.scale(scale, scale);
          
        var board = cast(tmpMc.createEmptyMovieClip("board",2), MovieClip);
          
        board.beginFill(0xFFFFFF);
        board.moveTo(-10 , -10);
        board.lineTo(w + 10, -10);
        board.lineTo(w+10, h + 50);
        board.lineTo(-10, h + 50);
        board.lineTo(-10, -10);
        board.endFill();
          
        var borderFilters = new Array<BitmapFilter>();
        borderFilters.push(borderShadow);
        board.filters = borderFilters;
          
        
        tmpMc.transform.matrix = transformMatrix;

        gr._alpha = 80;

        self = tmpMc;
        assignHandlers();
    }
    
    function assignHandlers():Void {
        self.onRollOver = onRollOver;
        self.onRollOut = onRollOut;
        self.onPress = onPress;
    }
    
    function onRollOver():Void {
        self.gr._alpha = 99;
    }
    
    function onRollOut():Void {
        self.gr._alpha = 80;
    }
    
    function onPress():Void {
        self.onEnterFrame = selfFade;
    }
    
    function selfFade():Void {
        self._alpha -= 12;
        self._x += 8;
        self._y += 8;
        if (self._alpha <= 0) {
            self._visible = false;
            //looks wierd, may waste memory
            self.onEnterFrame = null;
            onDissapear();
        }
    }
    
    public function onDissapear():Void {}
    
    public function showBack(){
        var l = Math.round(Math.random()*50) + 670;
        var t = Math.round(Math.random()*70) + 40;

        var bPhoto = new BackPhoto(note, l, t, self._width, self._height, parent);
        bPhoto.draw(parent.getNextHighestDepth());
        
    }
}