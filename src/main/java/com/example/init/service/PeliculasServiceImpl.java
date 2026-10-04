package com.example.init.service;

import com.example.init.model.PeliculasResponseDTO;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PeliculasServiceImpl implements PeliculasService, InitializingBean {

    private final VectorStore vectorStore;

    public PeliculasServiceImpl(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @Override
    public List<PeliculasResponseDTO> getPeliculas(String preferencia) {
        List<Document> documentos = vectorStore.similaritySearch(
                SearchRequest.builder()
                        .query(preferencia)
                        .build()
        );

        return documentos.stream()
                .map(document -> new PeliculasResponseDTO(
                        (String) document.getMetadata().get("titulo"),
                        (String) document.getMetadata().get("anio"),
                        Math.round(document.getScore() * 10000.0) / 100.0
                ))
                .toList();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        vectorStore.add(List.of(
                new Document(
                        "En un futuro en el que la Tierra sufre una grave crisis medioambiental y la humanidad está al borde de la extinción, un antiguo piloto de la NASA se une a una misión espacial secreta. Un grupo de astronautas atraviesa un agujero de gusano cercano a Saturno para explorar distintos planetas y encontrar un lugar habitable donde pueda sobrevivir la humanidad. La película combina viajes espaciales, exploración de otros mundos, relaciones familiares, relatividad temporal y sacrificio personal.",
                        Map.of(
                                "titulo", "Interstellar",
                                "anio", "2014"
                        )
                ),
                new Document(
                        "Thomas Anderson, un programador informático que lleva una doble vida como hacker bajo el nombre de Neo, descubre que la realidad que conoce es en realidad una simulación creada por máquinas inteligentes. Tras conocer a Morfeo y Trinity, se incorpora a una rebelión que intenta liberar a la humanidad de ese sistema. La historia mezcla ciencia ficción, inteligencia artificial, realidad virtual, programación, acción y cuestiones filosóficas sobre la naturaleza de la realidad.",
                        Map.of(
                                "titulo", "The Matrix",
                                "anio", "1999"
                        )
                ),
                new Document(
                        "Un multimillonario consigue crear mediante ingeniería genética dinosaurios vivos a partir de ADN recuperado de fósiles. Para demostrar la seguridad de su nuevo parque temático, invita a un grupo de científicos y especialistas a visitarlo antes de su apertura. Sin embargo, un fallo en los sistemas de seguridad permite que los animales escapen y conviertan la visita en una lucha por la supervivencia. La película combina aventuras, ciencia, genética, animales prehistóricos y suspense.",
                        Map.of(
                                "titulo", "Jurassic Park",
                                "anio", "1993"
                        )
                ),
                new Document(
                        "La película narra la historia de la familia Corleone, una poderosa organización criminal de origen italiano establecida en Nueva York. Don Vito Corleone dirige la familia utilizando una combinación de influencia, lealtad y violencia, pero su posición comienza a verse amenazada por otras organizaciones. Su hijo Michael, inicialmente alejado de los negocios familiares, acaba involucrándose progresivamente en ellos. La película aborda temas como la familia, el poder, la corrupción, la lealtad y la transformación personal.",
                        Map.of(
                                "titulo", "El Padrino",
                                "anio", "1972"
                        )
                ),
                new Document(
                        "Una joven perteneciente a una familia adinerada embarca en el Titanic, un enorme transatlántico que realiza su viaje inaugural hacia Estados Unidos. Durante el viaje conoce a Jack, un joven de origen humilde con quien establece una relación a pesar de las diferencias sociales y de la oposición de su entorno. La historia se desarrolla mientras el barco navega hacia el desastre que marcará la vida de todos sus pasajeros. La película combina romance, drama histórico, tragedia y supervivencia.",
                        Map.of(
                                "titulo", "Titanic",
                                "anio", "1997"
                        )
                ),
                new Document(
                        "En un mundo fantástico, un joven hobbit llamado Frodo hereda un poderoso anillo que pertenece al antiguo señor oscuro Sauron. El objeto debe ser destruido para impedir que Sauron recupere su poder y someta a todos los pueblos de la Tierra Media. Frodo emprende un largo viaje acompañado por otros hobbits, humanos, un elfo, un enano y un mago. La historia incluye magia, criaturas fantásticas, batallas, amistad, viajes y una lucha entre el bien y el mal.",
                        Map.of(
                                "titulo", "El Señor de los Anillos: La Comunidad del Anillo",
                                "anio", "2001"
                        )
                ),
                new Document(
                        "Marty McFly es un adolescente que accidentalmente viaja al pasado utilizando un automóvil convertido en máquina del tiempo por el excéntrico científico Doc Brown. Marty llega a una época anterior al nacimiento de sus padres y, debido a una intervención accidental, pone en peligro que ambos lleguen a conocerse. Para regresar a su época debe conseguir que sus padres se enamoren y encontrar una forma de generar la energía necesaria para activar nuevamente la máquina del tiempo. La película combina ciencia ficción, viajes temporales, humor y aventuras.",
                        Map.of(
                                "titulo", "Regreso al Futuro",
                                "anio", "1985"
                        )
                ),
                new Document(
                        "Máximo Décimo Meridio es un general romano que sirve fielmente al emperador Marco Aurelio. Tras una lucha por el poder, pierde a su familia y es convertido en esclavo. Obligado a convertirse en gladiador, consigue alcanzar una gran fama en los combates del Coliseo mientras busca vengarse de quienes destruyeron su vida. La película recrea la antigua Roma y combina batallas, política, venganza, honor, esclavitud y supervivencia.",
                        Map.of(
                                "titulo", "Gladiator",
                                "anio", "2000"
                        )
                ),
                new Document(
                        "Woody es el juguete favorito de un niño llamado Andy y ejerce como líder del resto de sus juguetes. Su posición cambia cuando Andy recibe a Buzz Lightyear, un moderno juguete que cree ser un auténtico astronauta enviado desde el espacio. La rivalidad inicial entre ambos termina convirtiéndose en una amistad cuando deben colaborar para regresar junto a Andy. La película trata sobre amistad, celos, identidad, pertenencia y el miedo a ser reemplazado.",
                        Map.of(
                                "titulo", "Toy Story",
                                "anio", "1995"
                        )
                ),
                new Document(
                        "En una pequeña localidad costera cuya economía depende del turismo, varios ataques mortales provocados por un enorme tiburón blanco generan el pánico entre los habitantes y visitantes. El jefe de policía intenta cerrar las playas, pero las autoridades locales se preocupan por las consecuencias económicas. Finalmente, un grupo formado por un policía, un oceanógrafo y un experimentado cazador de tiburones se embarca para localizar y acabar con el animal. La película combina suspense, terror, supervivencia y aventuras marítimas.",
                        Map.of(
                                "titulo", "Tiburón",
                                "anio", "1975"
                        )
                ),
                new Document(
                        "Guido, un hombre italiano de origen judío, construye una vida feliz junto a su esposa y su hijo. Durante la Segunda Guerra Mundial, la familia es enviada a un campo de concentración. Para proteger a su hijo del horror que los rodea, Guido intenta convencerlo de que todo lo que sucede forma parte de un enorme juego en el que deben conseguir puntos para ganar un premio. La película combina comedia, drama, amor familiar, guerra y sacrificio.",
                        Map.of(
                                "titulo", "La vida es bella",
                                "anio", "1997"
                        )
                ),
                new Document(
                        "Ethan Hunt es un agente secreto que trabaja para una organización de inteligencia internacional. Después de que una misión termine con la muerte de prácticamente todo su equipo, es acusado de ser un traidor y debe descubrir quién ha filtrado información secreta. Para demostrar su inocencia tendrá que infiltrarse en instalaciones de máxima seguridad y organizar operaciones extremadamente arriesgadas. La película combina espionaje, acción, tecnología, persecuciones y conspiraciones.",
                        Map.of(
                                "titulo", "Misión: Imposible",
                                "anio", "1996"
                        )
                ),
                new Document(
                        "Jack Torrance acepta trabajar como cuidador de un aislado hotel de montaña durante el invierno, acompañado por su esposa y su hijo pequeño. Las fuertes nevadas dejan al hotel completamente incomunicado y la familia comienza a experimentar sucesos extraños relacionados con el pasado del edificio. Jack empieza a sufrir una transformación psicológica mientras su hijo descubre que posee una capacidad especial para percibir acontecimientos sobrenaturales. La película combina terror psicológico, aislamiento, locura y fenómenos paranormales.",
                        Map.of(
                                "titulo", "El resplandor",
                                "anio", "1980"
                        )
                ),
                new Document(
                        "Forrest Gump es un hombre con una capacidad intelectual limitada pero con una extraordinaria determinación y una forma muy particular de entender el mundo. A lo largo de su vida participa accidentalmente en numerosos acontecimientos importantes de la historia reciente de Estados Unidos, mientras mantiene su amor por Jenny, una amiga de la infancia. La película recorre varias décadas y aborda temas como la amistad, el amor, la guerra, la discapacidad, la familia y el paso del tiempo.",
                        Map.of(
                                "titulo", "Forrest Gump",
                                "anio", "1994"
                        )
                ),
                new Document(
                        "Bob Parr es un antiguo superhéroe que ahora lleva una vida aparentemente normal junto a su esposa y sus tres hijos, todos ellos dotados de diferentes poderes. Aunque intenta adaptarse a una existencia rutinaria, recibe una misteriosa misión que le permite volver a utilizar sus habilidades. Pronto descubre que la misión forma parte de un plan mucho más peligroso y que necesitará la ayuda de toda su familia para enfrentarse a la amenaza. La película combina superhéroes, acción, familia, tecnología, humor y aventuras.",
                        Map.of(
                                "titulo", "Los Increíbles",
                                "anio", "2004"
                        )
                )
        ));
    }
}
