# SIA
## Requisitos:
tener matlab 2016 o superior

## Procedimientos de ejecución:
1° descargar los archivos.   
2° descomprimirlos en una carpeta deseada.  
3° abrir matlab.  
4° abrir el archivo genericNewtonIncremental.m en matlab.  
5° ejecutar el archivo genericNewtonIncremental.m con matlab.  

## Parámetros de configuración:

En el archivo configFile.txt se encuentra toda la configuración del programa.
Sus parámetros son los siguientes:

* input_file nombreArchivo.txt   
El archivo debe contener 3 columnas de igual dimensión las cuales las dos primeras son destinadas a valores de entrada y la tercera a valores de salida.

* neurons 2,num-1,num-2,...,num-n,1   
Este parámetro permite modificar la arquitectura de la red, cada num-i ingresado corresponde al numero de neuronas por capa oculta, mientras que el 2 inicial corresponde a la capa de entrada y el 1 final corresponde a la capa de salida
ejemplo: 2,10,20,1. es una arquitectura de red de dos neuronas de entrada, 10 neuronas de la primera capa oculta, 20 neuronas de la segunda capa oculta y una neurona de la capa de salida.

* epochs num  
Es el numero de épocas que se va a ejecutar el algoritmo de entrenamiento.

* training_size num  
Num debe ser menor o igual que el numero de filas del archivo de entrada, training_size es el numero de patrones para entrenamiento y el resto de los patrones de entrada (totalPatrones - num) es destinado a testeo.

* eta num  
Es un valor entre 0 y 1, el cual representa la tasa de aprendizaje de la red neuronal por cada iteración.

* error_treshold_flag bool  
bool es 0 para desactivar, 1 para activar.  
Este parámetro activa el umbral de error, el cual permite que llegado a cierto valor de error corte la ejecucion del programa.

* momentum_flag bool  
bool es 0 para desactivar, 1 para activar.  
Este flag activa/desactiva momentum una mejora al algoritmo de backpropagation. 

* adaptative_eta_flag bool  
bool es 0 para desactivar, 1 para activar.  
Este flag activa/desactiva adaptive learning rate una mejora al algoritmo de backpropagation.

* shuffle_flag bool  
bool es 0 para desactivar, 1 para activar.  
Permite por cada época correr los patrones de entrenamiento en orden aleatorio.

* load_seed_flag bool  
bool es 0 para desactivar, 1 para activar.  
Mediante este flag se setea si se va a cargar una semilla deseada o no (para ello ver también el parámetro seed_id).

* save_seed_flag bool  
bool es 0 para desactivar, 1 para activar.  
Mediante este flag se setea si se va a guardar la semilla de la ejecución o no.

* seed_id nombreSemilla  
Nombre de la semilla a cargar (debe estar en la misma carpeta que los archivos del programa).

* error_treshold_value num  
Si activo el flag error_treshold_flag, este parámetro representa la cota de error con la que se desea que corte el algoritmo.

* eta_check_steps num  
En caso de activar el flag adaptative_eta_flag, eta_check_steps representa la cantidad de epocas hasta aplicar la mejora adaptive learning rate.
num debe ser un numero natural.

* eta_increase_value num  
En caso de activar el flag adaptative_eta_flag, eta_increase_value representa el valor que se va a incrementar la tasa de aprendizaje (eta) siguiendo la lógica de adaptive learning rate.  
Es recomendable que num sea menor a 1 y mayor que cero.

* eta_decrease_factor num  
En caso de activar el flag adaptative_eta_flag, eta_decrease_factor representa el porcentaje que se va a decrementar la tasa de aprendisaje (eta) siguiendo la lógica de adaptive learning rate.  
Es recomendable que num sea menor a 1 y mayor que cero.

* alpha_momentum num  
En caso de activar el flag momentum_flag, alpha_momentum representa el porcentaje de la variacion del peso anterior a sumar al nuevo peso.

* epsilon_error num  
Este parámetro es la cota para calcular el porcentaje de aciertos de la red.  
Por ejemplo, si se elije un epsilon_error 0.01 todos los patrones los cuales el modulo de la salida esperada menos la salida real sean menor que 0.01 van pertenecer al porcentaje de acierto, mientras que los que son mayos no. Entonces una ves obtenida la cantidad de aciertos se la divide por el total y multiplica por cien y como resultado da el porcentaje de aciertos de la red.

* tanh bool  
bool 1 para activar el algoritmo backpropagation con tanh, 0 para activar el algoritmo backpropagation con exponencial.
