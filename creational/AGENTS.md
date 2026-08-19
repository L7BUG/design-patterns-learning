# CREATIONAL DESIGN PATTERNS

## OVERVIEW
6 completed modules demonstrating object creation mechanisms with unique pattern implementations.

## STRUCTURE
```
creational/
├── singleton/          # 5 single-threaded & multi-threaded implementations
├── factory-method/     # Notification hierarchy with factory
├── abstract-factory/   # UI theme families (Windows/Mac)
├── builder/            # Computer with step-by-step construction
├── prototype/          # ShapeCache with clone-based creation
└── factory-kit/        # MessageFactory with type-based instantiation
```

## WHERE TO LOOK
| Task | Location | Notes |
|------|----------|-------|
| Singleton variants | `singleton/src/main/java/com/l7bug/singleton/` | 5 implementations: eager, lazy, double-checked, static, enum |
| Factory pattern | `factory-method/src/main/java/com/l7bug/factorymethod/` | Notification + ConcreteNotification hierarchy |
| Abstract factory | `abstract-factory/src/main/java/com/l7bug/abstractfactory/` | GUIFactory creates Button/Checkbox families |
| Builder pattern | `builder/src/main/java/com/l7bug/builder/` | Computer class with Builder inner class |
| Prototype pattern | `prototype/src/main/java/com/l7bug/prototype/` | ShapeCache stores cloneable shapes |
| Factory kit | `factory-kit/src/main/java/com/l7bug/factorykit/` | MessageFactory with MessageType enum |

## CONVENTIONS
- **No Mockito**: Dependencies are simple; tests use direct instantiation
- **Scenario-based test names**: Method names describe behavior (`shouldCreateSingletonWhen...`)
- **Chinese comments**: Test files use Chinese for scenario descriptions
- **5 singleton variants**: Eager, lazy, double-checked locking, static holder, enum

## ANTI-PATTERNS
- **Self-contained modules**: Each pattern is independent with no cross-module dependencies
- **Complete implementations**: Unlike other directories, all modules have full src/ structure
- **Multiple singleton approaches**: Same pattern implemented 5 different ways for comparison
