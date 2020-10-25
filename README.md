# TCI G3

This project represents the G3 exercise for the TCI course

## Getting Started

`git clone https://github.com/Vladlgv/TCI_G3.git`
Use Intelij to run the project

### Prerequisites

Make sure you have java installed and java SDK 

```

To import java sdk in intelij go to Project Structure > SDKs > select SDK path

```

### Installing


## Running the tests

To run the tests inside intelij right click the java folder from the src/test forlder and select 'Run Tests in grouopAssignment....' you can also chose the option for coverage to determnine code coverage


### Coding style tests

Test to see if betting Round ID is properly generated

```

@Test
    public void test_BettingRoundID_GeneratesNumber() {
        //arrange
        //act
        bettingRoundID = (BettingRoundID) IDFactory.generateID("BettingRoundID");
        //assert
        Assertions.assertThat(bettingRoundID.getUniqueID()).isInstanceOf(UUID.class);
        Assertions.assertThat(bettingRoundID.getTimeStamp()).isNotNull();
        Assertions.assertThat(bettingRoundID).isNotNull();
    }

```


## Built With

* [Java](https://www.java.com/) - The language used

* [Gradle](https://gradle.org/) - Dependency Management


## Contributing
Read Requirements Document

## Versioning
Every respective first did his work on his own branch and afterwards we merged all three branches in the main branch 

## Authors
* **Gabriel Vlad Luca** - *GamblerCard class, DefaultGame class, BettingRoundID class, GamingMachine class* 
* **Tran Nguyen** - *GameRule class, GameMachine, CardID*
* **Yang Diqin** - *BetID class, BettingRound class, Cashier class*

See also the list of [contributors](https://github.com/Vladlgv/TCI_G3/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone who's code was used


