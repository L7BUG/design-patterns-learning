# STRUCTURAL DESIGN PATTERNS

## OVERVIEW
7 modules (2 complete, 5 empty skeletons) demonstrating object composition and class relationships.

## STRUCTURE
```
structural/
├── adapter/     ✅ MediaPlayer/AudioPlayer with MediaAdapter
├── bridge/      ✗ empty
├── composite/   ✗ empty
├── decorator/   ✅ Coffee with MilkDecorator/SugarDecorator/WhipDecorator
├── facade/      ✗ empty
├── flyweight/   ✗ empty
└── proxy/       ✗ empty
```

## WHERE TO LOOK
| Task | Location | Notes |
|------|----------|-------|
| Adapter core | `adapter/src/main/java/com/l7bug/adapter/MediaAdapter.java` | Implements MediaPlayer, delegates to AdvancedMediaPlayer |
| Adaptee interface | `adapter/src/main/java/com/l7bug/adapter/AdvancedMediaPlayer.java` | Incompatible interface (playVlc/playMp4) |
| Decorator base | `decorator/src/main/java/com/l7bug/decorator/Coffee.java` | Component interface (getDescription/getCost) |
| Abstract decorator | `decorator/src/main/java/com/l7bug/decorator/CoffeeDecorator.java` | Holds Coffee reference, delegates by default |
| Concrete decorators | `decorator/src/main/java/com/l7bug/decorator/*Decorator.java` | Milk, Sugar, Whip extend CoffeeDecorator |

## CONVENTIONS
- **Adapter role naming**: Source files use GOF role names (Adaptee, Adapter, Target) in Javadoc comments
- **Decorator chain**: Concrete decorators are leaf classes, no further inheritance

## ANTI-PATTERNS
- **71% skeleton**: 5/7 modules are pom.xml-only (bridge, composite, facade, flyweight, proxy)
- **No shared adapter example**: adapter module hardcodes format dispatch in MediaAdapter constructor instead of using a registry
