# SIA

## Requisitos:
* Tener instalado java 1.8 o superior.
* Tener instalado Intellij idea o Eclipse.
* Tener maven instalado.

## Procedimientos de ejecución:
1° Descargar el proyecto.  
2° Descomprimirlos en una carpeta deseada.  
3° En caso de tener Eclipse se recomienda ingresar en la carpeta tp3 mediante consola y tirar el comando mvn eclipse:eclipse.  
4° Importar el proyecto (tp3) a Intellij idea o Eclipse.  
5° Ejecutar la clase Main.  

## Parámetros de configuración: 
En el archivo configFile.txt se encuentra toda la configuración del programa. Sus parámetros son los siguientes:  

* SELECTION_METHOD_A nombreMetodoSeleccion  
Este parámetro permite elegir el método de selección A, el cual junto con el método de selección B se encargan de escoger una cantidad deseada de personajes por generación para cruzarlos. Se recomienda ver SELECTION_CANT.  
Los valores posibles para nombreMetodoSeleccion son: ELITE, ROULETTE, UNIVERSAL, BOLTZMANN_ROULETTE, TOURNAMENT_DET, TOURNAMENT_PROB, RANKING. 

* SELECTION_METHOD_B  nombreMetodoSeleccion  
Este parámetro permite elegir el método de selección B, el cual junto con el método de selección A se encargan de escoger una cantidad deseada de personajes por generación para cruzarlos. Se recomienda ver SELECTION_CANT.  
Los valores posibles para nombreMetodoSeleccion son: ELITE, ROULETTE, UNIVERSAL, BOLTZMANN_ROULETTE, TOURNAMENT_DET, TOURNAMENT_PROB, RANKING.

* SELECTION_PERCENT num  
num debe ser un numero entre 0 y 1 inclusive. SELECTION_PERCENT representa que porcentaje de los personajes seleccionados se seleccionan con el método de selección A y que porcentaje con el método de selección B. Se toma el total que se quiere seleccionar y num*total se selecciona con método A mientras que (1-num)*total se selecciona con el método B.  
Por ejemplo si num es 0.3 y se deben seleccionar 10 individuos, entonces 3 se van a seleccionar con el método A y 7 con el método B.

* CROSSING_METHOD nombreMetodoCruza  
Este parámetro permite seleccionar el método con el cual se van a cruzar los personajes. Los distintos valores posibles son: ONE_POINT, TWO_POINT, UNIFORM, ANNULAR.

* MUTATION_METHOD nombreMetodoMutacion  
Este parámetro permite seleccionar el método de mutación para los personajes. Los distintos valores posibles son: SINGLE_GEN_MUTATION (muta solo de a un item/altura), MULTI_GEN_MUTATION (muta uno o varios items/altura).

* MUTATION_TYPE nombreTipoMutacion  
Mediante este parámetro podemos elegir el tipo de mutación deseada. nombreTipoMutation puede ser UNIFORM o NO_UNIFORM. En caso de ser no uniforme ver MUTATION_PROB_DECREASE_PERCENT.

* REPLACEMENT_METHOD nombreMetodoReemplazo  
Con este parámetro se puede seleccionar el método de reemplazo deseado.  
Los posible valores para nombreMetodoReemplazo son: FIRST, SECOND, THIRD.

* REPLACEMENT_SELECTION_METHOD_A nombreMetodoSeleccion  
Este parámetro nos permite elegir el método de selección A para reemplazo, que junto con el método de selección B para reemplazo, es posible determinar parte o la totalidad de los personajes que pasan a la siguiente generación.   
Los valores posibles para nombreMetodoSeleccion son: ELITE, ROULETTE, UNIVERSAL, BOLTZMANN_ROULETTE, TOURNAMENT_DET, TOURNAMENT_PROB, RANKING. 

* REPLACEMENT_SELECTION_METHOD_B nombreMetodoSeleccion  
Este parámetro nos permite elegir el método de selección B para reemplazo, que junto con el método de selección A para reemplazo, es posible determinar parte o la totalidad de los personajes que pasan a la siguiente generación.  
Los valores posibles para nombreMetodoSeleccion son: ELITE, ROULETTE, UNIVERSAL, BOLTZMANN_ROULETTE, TOURNAMENT_DET, TOURNAMENT_PROB, RANKING. 

* REPLACEMENT_PERCENT num  
num debe ser un numero entre 0 y 1 inclusive. REPLACEMENT_PERCENT representa que porcentaje de los personajes seleccionados se seleccionan con el método de selección A para reemplazo y que porcentaje con el método de selección B para reemplazo. Se toma el total que se quiere seleccionar y num*total se selecciona con método A mientras que (1-num)*total se selecciona con el método B.

* CHARACTER_TYPE tipoPersonaje  
Mediante este parámetro se selecciona el tipo de personaje en base al cual se va a correr el algoritmo genético.  
Los distinto valores posibles son: ARCHER1, ARCHER2, ARCHER3, ASSASSIN1, ASSASSIN2, ASSASSIN3, DEFENDER1, DEFENDER2, DEFENDER3, WARRIOR1, WARRIOR2, WARRIOR3.

* MUTATION_PROB num  
num es un valor entre 0 y 1 inclusive. MUTATION_PROB representa la probabilidad de que un personaje mute. Se tira un numero al azar entre 0 y 1, y si el valor es menor a num un personaje muta, caso contrario no muta.

* MUTATION_PROB_DECREASE_PERCENT num  
num es un numero real entre 0 y 100.  
Este parámetro es utilizado para la mutación no uniforme, MUTATION_PROB_DECREASE_PERCENT representa el porcentaje que decrece la probabilidad de mutación generación a generación hasta llegar a un valor mínimo el cual es 0.1.

* CROSSING_PROB num  
num es un valor entre 0 y 1.  
Este parámetro nos permite seleccionar la probabilidad de que dos personajes se crucen formando dos nuevos. Se tira un numero al azar entre 0 y 1, y si el valor es menor a num los personajes se cruzan formando dos personajes nuevos para la nueva generación, caso contrario ellos dos pasan a la nueva generación.

* POPULATION_CANT num  
num es un numero natural. POPULATION_CANT representa la cantidad de personajes que va a haber en cada generación.

* SELECTION_CANT num  
num es un numero natural menor al de POPULATION_CANT.
Este parámetro representa la cantidad de personajes que son seleccionados mediante algún método de selección para luego ser cruzados. Ademas representa la cantidad de hijos que se van a generar de los personajes anteriormente seleccionados.

* TEMP num  
num es un numero real.  
Este parámetro es utilizado con el método de selección BOLTZMANN_ROULETTE y representa la temperatura del mismo.

* EXPONENTIAL_FACTOR num  
num es un numero real.   
Este parámetro es utilizado con el método de selección BOLTZMANN_ROULETTE, es un factor que forma parte del exponente de una exponencial negativa. A mayor valor de factor menor temperatura, mientras que a menor mayor temperatura.

* TOURNAMENT_CANT_COMPETITORS num  
num es un numero natural.  
Este parámetro es utilizado con el método de selección TOURNAMENT_DET y representa la cantidad de competidores por cada selección de un personajes. Es decir si num es 3 se eligen 3 personajes al azar de esa generación y gana el que tenga mejor fitness.

* TOURNAMENT_PROB num  
num es un numero entre 0 y 1.  
Este parámetro es utilizado por el método de selección TOURNAMET_PROB. Se utiliza cuando, se seleccionan dos personajes al azar de la generación y se pide un numero random p entre 0 y 1 si el numero p es menor a num entonces se selecciona el de mejor fitness, caso contrario se selecciona el de peor fitness.

* GENERATIONS num  
num es un numero natural. GENERATIONS es un parámetro de corte mediante el cual se le indica al algoritmo la cantidad de generaciones deseadas hasta que este termine su ejecución.

* FITNESS_OPT num  
num es un numero real. FITNESS_OPT es un parámetro de corte el cual representa el fitness optimo que se desea obtener para que el algoritmo termine su ejecución.
Ver también EPSILON.

* EPSILON num  
num es un numero real. EPSILON representa el porcentaje de tolerancia que se desea aceptar en el caso de corte por fitness optimo. es decir que si encuentro un fitness f el cual f + num > fitnessOpt termino la ejecución del algoritmo.

* RANDOM num  
num es un numero natural. RANDOM representa la semilla que se desea setear para correr el algoritmo genético.

* GENERATION_CHECK num  
num es un numero natural.   
Este parámetro representa cada cuantas generaciones chequeo o el aumento del máximo fitness o el aumento del fitness promedio de la población. Este aumento antes mencionado se setea en GENERATION_INC.

* GENENERATION_INC num  
num es un numero real entre 0 y 100 representa un porcentaje. GENERATION_INC es un parámetro de corte el cual corrobora que desde la ultima vez chequeado el fitness máximo o promedio halla subido un porcentaje deseado si esto no es así termina la ejecución del algoritmo.

* CONTENT_FLAG bool  
bool o es para desactivar, 1 para activar.  
Cuando este flag esta encendido, se chequea cada x generaciones que el máximo fitness supere en un porcentaje deseado el máximo fitness de una generación anterior.  
Ver GENERATION_INC y GENERATION_CHECK.

* STRUCTURE_FLAG bool  
bool o es para desactivar, 1 para activar.  
Cuando este flag esta encendido, se chequea cada x generaciones que el fitness promedio supere en un porcentaje deseado el fitness promedio de una generación anterior.  
Ver GENERATION_INC y GENERATION_CHECK.

* OPT_FLAG bool  
bool o es para desactivar, 1 para activar.  
Este flag activa y desactiva el parámetro FITNESS_OPT.  

* ITERATIONS_FLAG bool  
bool o es para desactivar, 1 para activar.  
Este flag activa y desactiva el parámetro GENERATIONS.  

* GRAPHIC_FLAG bool  
bool o es para desactivar, 1 para activar.  
Mediante este parámetro activamos o desactivamos los gráficos en la ejecución del algoritmo genético.  

* RAID_FLAG bool  
bool o es para desactivar, 1 para activar.  
Mediante este parámetro activamos el Raid o el algoritmo genético, es decir si el valor es 1 se activa el raid, caso contrario se activa el algoritmo genético.

* TIME_FLAG bool  
bool o es para desactivar, 1 para activar.  
Mediante este parámetro activamos o desactivamos el parámetro TIME.

* TIME num  
num es un numero natural. TIME representa el tiempo que se desee correr el algoritmo genético.

* ARMOR_FILE nombreArchivo.xls  
Mediante este parámetro cargamos las pecheras para el algoritmo genético.  
El archivo debe contener 6 columnas de igual dimensión las cuales en la primera se encuentra el id, en la segunda la fuerza, en la tercera la agilidad, en la cuarta la destreza, en la quinta la resistencia y en la sexta la vida.

* BOOTS_FILE nombreArchivo.xls  
Mediante este parámetro cargamos las botas para el algoritmo genético.  
El archivo debe contener 6 columnas de igual dimensión las cuales en la primera se encuentra el id, en la segunda la fuerza, en la tercera la agilidad, en la cuarta la destreza, en la quinta la resistencia y en la sexta la vida.

* GLOVES_FILE nombreArchivo.xls  
Mediante este parámetro cargamos los guantes para el algoritmo genético.  
El archivo debe contener 6 columnas de igual dimensión las cuales en la primera se encuentra el id, en la segunda la fuerza, en la tercera la agilidad, en la cuarta la destreza, en la quinta la resistencia y en la sexta la vida.

* HELMET_FILE nombreArchivo.xls  
Mediante este parámetro cargamos los cascos para el algoritmo genético.  
El archivo debe contener 6 columnas de igual dimensión las cuales en la primera se encuentra el id, en la segunda la fuerza, en la tercera la agilidad, en la cuarta la destreza, en la quinta la resistencia y en la sexta la vida.

* WEAPON_FILE nombreArchivo.xls  
Mediante este parámetro cargamos las armas para el algoritmo genético.  
El archivo debe contener 6 columnas de igual dimensión las cuales en la primera se encuentra el id, en la segunda la fuerza, en la tercera la agilidad, en la cuarta la destreza, en la quinta la resistencia y en la sexta la vida.
