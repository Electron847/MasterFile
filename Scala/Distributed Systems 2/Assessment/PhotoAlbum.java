import java.util.*;


public class PhotoAlbum {

    int position;
    Photo[] PhotoAlbum;

    public PhotoAlbum () {PhotoAlbum = new Photo[1]; position = PhotoAlbum.length;}

    public int getCurrent(){return position;}
    public Photo setCurrent(int position){
        this.position = current().getPositionIndex();
        return PhotoAlbum[getCurrent()];
    }

    public Photo insert(Photo photo){
        int PAlength = PhotoAlbum.length;
        System.out.println("length of PhotoAlbum before insertion is "+ PAlength);
        PhotoAlbum[0] = photo;
        Photo[] proxyAlbum = new Photo[PAlength + 1];
        //int indexOfInsertion = proxyAlbum.length - PAlength;
        System.out.println("proxyAlbum length is "+proxyAlbum.length);
        for (int i = 0; i < PAlength; i++){
            proxyAlbum[i+1] = PhotoAlbum[i];
            System.out.println("photo ID "+PhotoAlbum[i]+" entered into PhotoAlbum");
            //proxyAlbum[i+1] = PhotoAlbum[i];
        }

        System.out.println("at index "+0+" we place the photo.id value of "+photo.id);
        PhotoAlbum = proxyAlbum;
        return photo;
    }

    public Photo current(){return PhotoAlbum[position];}
    public Photo next(){
        position = position + 1;
        try{
            return PhotoAlbum[position];
        } catch(IndexOutOfBoundsException x){System.out.println("End");}
        return null;
    }
    public Photo prev(){
        position = position - 1;
        try{
            if(PhotoAlbum[position]==null){System.out.println("Awaiting insertion of photo");}
            return PhotoAlbum[position];
        } catch(IndexOutOfBoundsException x){System.out.println("index position error");}
        return null;
    }

    public void delete(){
        int arrayLengthDelete = PhotoAlbum.length;
        if(PhotoAlbum==null || position <0 || position >= arrayLengthDelete+1){
            System.out.println("nothing to delete");
        }

        else {
            Photo[] photoAlbumDelete = new Photo[arrayLengthDelete - 1];

            for(int i=0; i<arrayLengthDelete;i++){

                if(i == position){continue;}
                photoAlbumDelete[i++] = PhotoAlbum[i];
            }
            PhotoAlbum = photoAlbumDelete;
        }
    }

    public static void main(String[] args){

        PhotoAlbum PhotoAlbum = new PhotoAlbum();
        Photo photo1 = new Photo("10");
        photo1.positionIndex = 0;
        Photo photo2 = new Photo("20");
        photo2.positionIndex = 1;
        Photo photo3 = new Photo("30");
        photo3.positionIndex = 2;

        PhotoAlbum.insert(photo1);
        PhotoAlbum.insert(photo2);
        PhotoAlbum.insert(photo3);

        PhotoAlbum.current();
        PhotoAlbum.next();
        PhotoAlbum.prev();
        PhotoAlbum.delete();

        System.out.println("Current Photo ID of "+PhotoAlbum.current()+" as entry number "+(PhotoAlbum.current().getPositionIndex() + 1));
    }
}

class Photo {
    int positionIndex;
    Photo(int positionIndex)
    {this.positionIndex = positionIndex;}
    public int getPositionIndex()
    {return positionIndex;}

    String id;
    Photo(String id)
    {this.id = id;}
    public String toString()
    {return this.id;}
}