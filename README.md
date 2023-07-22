Спасибо Андрею Беляеву за пропаганду Entity Graph.

Собственно мысль.

1. Хочу сделать апи, которое будет выковыривать любое Entity или List<Entity> при помощи динамического EntityGraph
2. Клиентский код, должен быть вида:

   для одной сущности:
  *  AnyEntity entity = entityGraphExtractor.createContext(persitedEntity)
  *                        .with...
  *                        .with...And...
  *                        .with...And...And...
  *                        .extractOne
   и для коллекции:
  *  List<AnyEntity> entities = entityGraphExtractor.createContext(persistedEntities)
  *                        .with...
  *                        .with...And...
  *                        .with...And...And...
  *                        .extractAll


   

Хочу чтобы стартер сканировал все классы.
Искал @Entity или спринг дата репозитории. 
Или если будет сложно - то свой интерфейс или аннотация.
И создавал класс на лету.

Этот класс, должен предоставлять апи вида.



При этом под капотом, должен собираться entity graph
а так же, чтобы работало с коллекциями id'шников сущностей
