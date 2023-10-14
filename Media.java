import tester.Tester;


// a piece of media
interface IMedia {

  // is this media really old?
  boolean isReallyOld();

  // are captions available in this language?
  boolean isCaptionAvailable(String language);

  // a string showing the proper display of the media
  String format();
}
// represent a form of media
abstract class AMedia implements IMedia {
  String title;
  ILoString captionOptions;
  AMedia(String title, ILoString captionOptions) {
    this.title = title;
    this.captionOptions = captionOptions;
  }
  /*
  TEMPLATE:
  Fields:
  - this.title -- String
  - this.captionOptions -- ILoString
  Methods:
  - this.isReallyOld() -- boolean
  - this.isCaptionAvailable() -- boolean
  - this.format() -- String
   */
  // determine whether a piece of media qualifies as old
  public abstract boolean isReallyOld();
  // determine if the caption options of the given form of media contains the given language
  public boolean isCaptionAvailable(String language) {
    return this.captionOptions.contains(language);
  }
  // write the details of a piece of media in a specified format
  public abstract String format();
}

// represents a movie
class Movie extends AMedia {
  int year;
  Movie(String title, int year, ILoString captionOptions) {
    super(title, captionOptions);
    this.year = year;
  }
  public boolean isReallyOld() {
    return year < 1930;
  }
  public String format() {
    return this.title + " (" + this.year + ")";
  }
}

// represents a TV episode
class TVEpisode extends AMedia {
  String showName;
  int seasonNumber;
  int episodeOfSeason;
  TVEpisode(String title, String showName, int seasonNumber, int episodeOfSeason,
            ILoString captionOptions) {
    super(title, captionOptions);
    this.showName = showName;
    this.seasonNumber = seasonNumber;
    this.episodeOfSeason = episodeOfSeason;
  }
  // determine if a TVEpisode is old
  public boolean isReallyOld() {
    return false;
  }
  // format the TVEpisodes' details into the given format.

  public String format() {
    return this.showName + " " + this.seasonNumber + "." + this.episodeOfSeason + " - " + this.title;
  }
}

// represents a YouTube video
class YTVideo extends AMedia {
  String channelName;
  YTVideo(String title, String channelName, ILoString captionOptions) {
    super(title, captionOptions);
    this.channelName = channelName;
  }
  // Determine if the YTVideo is old
  public boolean isReallyOld() {
    return false;
  }
  // Format the YTVideos' details into the given format.

  public String format() {
    return this.title + " by " + this.channelName;
  }
}

// lists of strings
interface ILoString {

  boolean contains(String language);
}

// an empty list of strings
class MtLoString implements ILoString {
  public boolean contains(String language) {
    return false;
  }
}

// a non-empty list of strings
class ConsLoString implements ILoString {
  String first;
  ILoString rest;

  ConsLoString(String first, ILoString rest) {
    this.first = first;
    this.rest = rest;
  }
  public boolean contains(String language) {
    if (this.first.equals(language)) {
      return true;
    } else {
      return this.rest.contains(language);
    }
  }
}

class ExamplesMedia {
  Movie MovieExample1 = new Movie("Your Name", 2016, new ConsLoString("English", new ConsLoString("Spanish", new MtLoString())));
  Movie MovieExample2 = new Movie("Old", 1922, new ConsLoString("English", new ConsLoString("Spanish", new MtLoString())));
  TVEpisode TVExample1 = new TVEpisode("Beginnings", "Spongebob", 1, 1, new ConsLoString("English", new ConsLoString("Spanish", new MtLoString())));
  YTVideo YTVideoExample1 = new YTVideo("How to Basic", "How2Basic", new ConsLoString("English", new ConsLoString("Spanish", new MtLoString())));
  boolean testIsReallyOld1 (Tester t) {
    return t.checkExpect(this.MovieExample1.isReallyOld(), false);
  }
  boolean testIsReallyOld2(Tester t) {
    return t.checkExpect(this.MovieExample2.isReallyOld(), true);
  }
  boolean testIsReallyOld3 (Tester t) {
    return t.checkExpect(this.YTVideoExample1.isReallyOld(), false);
  }
  boolean testIsCaptionAvailable1 (Tester t) {
    return t.checkExpect(this.MovieExample2.isCaptionAvailable("English"), true);
  }
  boolean testIsCaptionAvailable2 (Tester t) {
    return t.checkExpect(this.TVExample1.isCaptionAvailable("French"), false);
  }
  boolean testFormatMovie (Tester t) {
    return t.checkExpect(this.MovieExample1.format(), "Your Name (2016)");
  }
  boolean testFormatTVEpisode (Tester t) {
    return t.checkExpect(this.TVExample1.format(), "Spongebob 1.1 - Beginnings");
  }
  boolean testFormatYTVideo (Tester t) {
    return t.checkExpect(this.YTVideoExample1.format(), "How to Basic by How2Basic");
  }
}