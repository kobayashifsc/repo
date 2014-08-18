import flash.filters.DropShadowFilter;
import flash.filters.BitmapFilter;
import flash.geom.Matrix;
import flash.MovieClip;

class BackPhoto {
    var self:MovieClip;
    var width:Float;
    var height:Float;
    var parent:MovieClip;
    var borderShadow:DropShadowFilter;
    var transformMatrix:Matrix;
    var text:String;
    
    public function new(text:String, left:Int, top:Int, width:Float, height: Float, parent:MovieClip) {
        this.text = text;
        this.parent = parent;
        this.width = width;
        this.height = height;
        borderShadow = new DropShadowFilter(4, 45, 0x000000, 0.6, 6, 6, 2, 4);
        
        this.transformMatrix = new Matrix();
        var scaleX:Float = .7;
        var scaleY:Float = .7;
        transformMatrix.scale(scaleX, scaleY);
        var angle =  - 8 + Math.floor(Math.random() * 16);
        var rotation:Float = 2 * Math.PI * (angle / 360);
        transformMatrix.rotate(rotation);
        transformMatrix.translate(left, top);
        
    }
    
    public function draw(depth:Int) {
        var tmpMc = parent.createEmptyMovieClip("back" + depth, depth);
        
        var border = tmpMc.createEmptyMovieClip("border",2);
        border.beginFill(0xFFFFFF);
        border.moveTo(-10 , -10);
        border.lineTo(width + 10, -10);
        border.lineTo(width + 10, height + 50);
        border.lineTo(-10, height + 50);
        border.lineTo(-10, -10);
        border.endFill();
        
        var note = tmpMc.createEmptyMovieClip("note",tmpMc.getNextHighestDepth());
        var textField = note.createTextField("textField", 0, 10, 10, width - 10, height);
        
        textField.multiline = true;
        textField.wordWrap = true;
        textField.text = this.text;
        
        var tff = new flash.TextFormat();
        tff.color = 0x7D1B7E;
        tff.url = "http://localhost";
        tff.bold = true;
        tff.size = 24;
        tff.font = "DS Note";
        
        textField.setTextFormat(tff);
        
        textField.embedFonts = true;
        textField.type = "dynamic";
        

        var borderFilters = new Array<BitmapFilter>();
        borderFilters.push(borderShadow);
        border.filters = borderFilters;
        
        tmpMc.transform.matrix = transformMatrix;
        this.self = tmpMc;
    }
    
}
