
package com.vgdatabase304.Structures;

public enum Platform {
    Odyssey("Magnavox Odyssey"),
    CommodorePET("Commodore PET"),
    Pinball("Pinball"),
    Redemption("Redemption"),
    AdventureVision("Entex Adventure Vision"),
    APFMP1000("APF MP1000"),
    AppleII("AppleII"),
    Arcadia2001("Arcadia 2001"),
    Astrocade("Bally Astrocade"),
    Atari2600("Atari 2600"),
    Atari5200("Atari 5200"),
    Atari8Bit("Atari 8Bit"),
    BBCMIcro("BBC Micro"),
    CassetteVision("Epoch Cassette Vision"),
    ChannelF("Fairchild Channel"),
    ColecoVision("ColecoVision"),
    CreatiVision("VTech CreatiVision"),
    EACAColourGenie2000("EACA Colour Genie 2000"),
    Intellivision("Intellivision"),
    IntertonVC4000("Interton VC4000"),
    MattelAquarius("Mattel Aquarius"),
    MicroVision("MicroVision"),
    NECPC88("NEC PC88"),
    Odyssey2("Odyssey2"),
    RCAStudioII("RCAStudioII"),
    SG1000("SG1000"),
    SordM5("Sord M5"),
    TandyColorComputer("Tandy Color Computer"),
    TI994A("TI-99 4A"),
    TomyTutor("Tomy Tutor"),
    Vectrex("Vectrex"),
    VIC20("VIC-20"),
    AmstradCPC("Amstrad CPC"),
    Atari7800("Atari 7800"),
    AtariST("Atari ST"),
    BBSDoor("BBS Door"),
    Commodore64("Commodore 64"),
    FamicomDiskSystem("Famicom Disk System"),
    FM7("FM7"),
    MSX("MSX"),
    NECPC98("NEC PC98"),
    NES("NES"),
    Oric1Atmos("Oric 1 Atmos"),
    SegaMasterSystem("Sega Master System"),
    SharpX1("Sharp X1"),
    SinclairZX81Spectrum("Sinclair ZX81 Spectrum"),
    SuperCassetteVision("Super Cassette Vision"),
    AcornArchimedes("Acorn Archimedes"),
    Amiga("Amiga"),
    CDI("CDI"),
    GameBoy("Game Boy"),
    GameGear("GameGear"),
    Genesis("Genesis"),
    LaserActive("LaserActive"),
    Lynx("Lynx"),
    NeoGeoCD("Neo-Geo CD"),
    NeoGeo("NeoGeo"),
    OS2("OS2"),
    Sega32X("Sega 32X"),
    SegaCD("Sega CD"),
    SharpX68000("Sharp X68000"),
    SuperNintendo("Super Nintendo"),
    SuperVision("SuperVision"),
    TurboCD("Turbo CD"),
    TurboGrafx16("TurboGrafx16"),
    ThreeDO("ThreeDO"),
    AmigaCD32("Amiga CD32"),
    BandaiPippin("Bandai Pippin"),
    CasioLoopy("Casio Loopy"),
    CPSChanger("CPS Changer"),
    FMTowns("FM Towns"),
    GameBoyColor("Game Boy Color"),
    Gamecom("Game.com"),
    Jaguar("Jaguar"),
    JaguarCD("Jaguar CD"),
    Nintendo64("Nintendo 64"),
    Nintendo64DD("Nintendo 64DD"),
    PalmOSClassic("Palm OS Classic"),
    PCFX("PCFX"),
    Playdia("Playdia"),
    PlayStation("PlayStation"),
    Saturn("Saturn"),
    VirtualBoy("Virtual Boy"),
    WonderSwan("WonderSwan"),
    Dreamcast("Dreamcast"),
    DVDPlayer("DVD Player"),
    eReader("eReader"),
    GameBoyAdvance("Game Boy Advance"),
    GameCube("GameCube"),
    Gizmondo("Gizmondo"),
    GP32("GP32"),
    NGage("NGage"),
    NeoGeoPocketColor("NeoGeo Pocket Color"),
    Nuon("Nuon"),
    PlayStation2("PlayStation 2"),
    WonderSwanColor("WonderSwan Color"),
    Xbox("Xbox"),
    Zodiac("Zodiac"),
    BlackBerry("BlackBerry"),
    DS("DS"),
    Flash("Flash"),
    Mobile("Mobile"),
    PalmwebOS("Palm webOS"),
    PlayStation3("PlayStation 3"),
    PSP("PSP"),
    Wii("Wii"),
    Xbox360("Xbox 360"),
    Zeebo("Zeebo"),
    ThreeDS("3DS"),
    AmazonFireTV("Amazon Fire TV"),
    Android("Android"),
    ArcadeGames("Arcade Games"),
    iOS("iOS"),
    Linux("Linux"),
    Macintosh("Macintosh"),
    NintendoSwitch("Nintendo Switch"),
    Online("Online"),
    Ouya("Ouya"),
    PC("PC"),
    PlayStation4("PlayStation 4"),
    PlayStationVita("PlayStation Vita"),
    WiiU("Wii U"),
    WindowsMobile("Windows Mobile"),
    XboxOne("Xbox One");

    private final String fullName;

    Platform(String name) {
        this.fullName = name;
    }

    public String getName() {
        return fullName;
    }

}
