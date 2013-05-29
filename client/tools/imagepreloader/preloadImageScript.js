function preloadImageScript(url, callback) {
    var preloadedImage = new Image();
    if( callback != null )
        preloadedImage.onload = callback;// function() { alert('preload OK'); };
    preloadedImage.src = url;
}