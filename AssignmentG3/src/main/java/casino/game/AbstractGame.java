package casino.game;


import gamblingauthoritiy.BetLoggingAuthority;

abstract class AbstractGame implements IGame{
    private BetLoggingAuthority betLoggingAuthority;
 // define only the constructor here
    public AbstractGame(BetLoggingAuthority betLoggingAuthority){
        this.betLoggingAuthority=betLoggingAuthority;
    }


}
