import flash.MovieClip;

class Gallery {
    var photos:Array<Photo>;
    var parent:MovieClip;
    var self:MovieClip;
    var notes:Array<String>;
    
    function fillNotes():Void {
        notes.push("Кот в банке!");
        notes.push("Дипломатические отношения");
        notes.push("тень");
        notes.push("робокот");
        notes.push("шок");
    }
    
    //shuffles loaded photos
    function shuffleArray(arr:Array<Dynamic>):Void {
        var size = arr.length;
        //not using iterator since I want to swap element of Array
        for (i in 0...size) {
            var l = Math.floor(Math.random() * size);
            var r = Math.floor(Math.random() * size);
            var t = arr[l];
            arr[l] = arr[r];
            arr[r] = t;
        }
    }
    
    public function new(parent:MovieClip) {
        this.parent = parent;
        self = parent.createEmptyMovieClip("gallery",parent.getNextHighestDepth());
        photos = new Array<Photo>();
        //initializing notes
        notes = new Array<String>();
        fillNotes();
        //~ shuffleArray(notes);
        //initializing photos
        for (i in 1...6) {
            var left = 60 + Math.floor(Math.random() * 410);
            var top = 40 + Math.floor(Math.random()* 300);
            photos.push(new Photo("photo" + i, left, top, notes[i-1], self));
        }
        shuffleArray(photos);

    }
    
    public function draw():Void {
        for (photo in photos.iterator()) {
            photo.draw(self.getNextHighestDepth());
            photo.onDissapear = photo.showBack;
        }
    }
}
