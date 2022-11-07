import java.util.Objects;

record Song(String artist, String title, int year) {

   public String toString() {
      return "\"" + title + "\" by " + artist + " (" + year + ")";
   }

   public boolean equals(final Object other) {
      if (this == other) {
         return true;
      }

      return other != null
              && other.getClass() == getClass()
              && Objects.equals(artist, ((Song) other).artist)
              && Objects.equals(title, ((Song) other).title)
              && year == ((Song) other).year;
   }

}

