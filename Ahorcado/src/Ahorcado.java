import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Ahorcado {

	// Método main - Menú en el que te hace la introducción y envía a los diferentes
	// métodos/opciones.
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean flagMenu = false;
		int opcionMenu;

		while (flagMenu == false) {
			System.out.println("Bienvenido al magnífico juego del...");
			System.out.println("    _    _   _  ___  ____   ____    _    ____   ___  ");
			System.out.println("   / \\  | | | |/ _ \\|  _ \\ / ___|  / \\  |  _ \\ / _ \\ ");
			System.out.println("  / _ \\ | |_| | | | | |_) | |     / _ \\ | | | | | | |");
			System.out.println(" / ___ \\|  _  | |_| |  _ <| |___ / ___ \\| |_| | |_| |");
			System.out.println("/_/   \\_\\_| |_|\\___/|_| \\_\\\\____/_/   \\_\\____/ \\___/ ");
			System.out.println();
			System.out.println("                          Creado por: Víctor Jurado®");
			System.out.println("----------------------------------------------------");
			System.out.println("|                       MENÚ                       |");
			System.out.println("----------------------------------------------------");
			System.out.println("| 1. Palabras aleatorias      2. Insertar palabras |");
			System.out.println("| 3. Salir                                         |");
			System.out.println("----------------------------------------------------");
			System.out.println();

			// Try-catch que recoge los errores de typeo en el menú, evita letras y números
			// no deseados.
			try {
				System.out.print("Opción: ");
				opcionMenu = sc.nextInt();
				switch (opcionMenu) {
				case 1:
					palabrasAleatorias();
					break;
				case 2:
					insertarPalabras();
					break;
				case 3:
					System.out.println("Espero que te hayas divertido.");
					flagMenu = true;
					break;
				default:
					System.out.println("\u001B[31m[ERROR]\u001B[0m Opción errónea, elija un número entre 1 y 3...");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println(
						"\u001B[31m[ERROR]\u001B[0m Debes ingresar un número entre 1 y 3. Inténtalo de nuevo.");
				System.out.println();
				sc.nextLine();
			}
		}
	}

	// Método "palabrasAleatorias" - Contiene una serie de palabras predefinidas
	// para jugar, se incluyen en un array y gracias al Random, cambia la palabra en
	// cada partida. Llama al método "jugarAhorcado" una vez seleccionada, aporta un
	// array con la palabra descompuesta por letras y la palabra como String.
	public static void palabrasAleatorias() {
		Random ra = new Random();
		int aleatorio = ra.nextInt(7) + 1;
		String arrayPalabras[] = { "Sol", "Pato", "Coche", "Atasco", "Impacto", "Cachorro", "Esperanza", "Estructura" };
		String acierto = arrayPalabras[aleatorio];
		String palabraAleatoria[] = acierto.split("");
		juegoAhorcado(palabraAleatoria, acierto);
	}

	// Método "insertarPalabras" - Permite introducir una palabra de manera manual
	// para jugar. Llama al método "jugarAhorcado" al igual que el anterior método.
	public static void insertarPalabras() {
		Scanner let = new Scanner(System.in);
		System.out.print("Introduce la palabra que quieres para jugar: ");
		String acierto = let.nextLine();
		String palabra[] = acierto.split("");
		juegoAhorcado(palabra, acierto);
	}

	// Método "juegoAhorcado" - Es donde se cocina la "grasa", incluye las
	// instrucciones y un menú que distingue entre insertar palabra, resolver o
	// rendirse.
	public static void juegoAhorcado(String[] palabra, String acierto) {
		Scanner let = new Scanner(System.in);
		Scanner sc = new Scanner(System.in);

		String intento;
		int opcionMenu = 0;
		int vidas = 6;

		// Genera un array del mismo tamaño que el de la palabra correcta, en este caso
		// se rellena con guiones bajos, más tarde se irá rellenando con las
		// coincidencias al insertar letras.
		String arrayAciertos[] = new String[palabra.length];
		for (int l = 0; l < arrayAciertos.length; l++) {
			arrayAciertos[l] = "_";
		}

		System.out.println();
		System.out.println("\u001B[33m*Redoble de tambores*\u001B[0m");
		System.out.println("Bienvenido/a al juego del ahorcado, estas son las reglas:");
		System.out.println("\u001B[33mI.\u001B[0m Tienes 6 vidas en las que introducir letras.");
		System.out.println("\u001B[33mII.\u001B[0m Si fallas un intento, pierdes una vida, si aciertas las mantienes.");
		System.out.println("\u001B[33mIII.\u001B[0m Asegurate si vas a resolver pues si te equivocas, perderás.");
		System.out.println();
		System.out.print("Palabra a adivinar: ");
		for (int i = 0; i < palabra.length; i++) {
			System.out.print("_ ");
		}

		while (vidas > 0) {
			boolean coincidencia = false;

			cuerpoAhorcado(vidas);
			System.out.println();
			System.out.print("Vidas totales: ");
			vidasAhorcado(vidas);
			System.out.println();
			System.out.println("----------------------------------------------------");
			System.out.println("|                       MENÚ                       |");
			System.out.println("----------------------------------------------------");
			System.out.println("|   1. Palabra      2. Resolver      3. Rendirme   |");
			System.out.println("----------------------------------------------------");
			System.out.println();

			// Try-catch que recoge los errores de typeo en el menú, evita letras y números
			// no deseados.
			try {
				System.out.print("Opción: ");
				opcionMenu = sc.nextInt();

				if (opcionMenu == 1) {
					System.out.print("Letra: ");
					intento = let.nextLine();

					// Restringe que al insertar letras solo se pueda usar un única letra, no más de
					// una, tampoco números.
					if (!intento.matches("[a-zA-Z]")) {
						System.out.println("\u001B[31m[ERROR]\u001B[0m Debes ingresar una letra. Inténtalo de nuevo.");

					} else {
						// Valida las coincidencias de letra.
						for (int j = 0; j < palabra.length; j++) {
							if (intento.equalsIgnoreCase(palabra[j])) {
								arrayAciertos[j] = palabra[j];
								coincidencia = true;
							}
						}

						// Rellena el array vacío con las coincidencias y lo imprime.
						System.out.print("Resultado: ");
						for (int m = 0; m < arrayAciertos.length; m++) {
							System.out.print(arrayAciertos[m] + " ");
						}
						System.out.println();

						// Resta una vida cuando no encuentra ninguna coincidencia y llegado a 0, emite
						// un mensaje y llama tanto al método "cuerpoAhorcado" como "muerte", veremos su
						// uso más tarde.
						if (coincidencia == false) {
							vidas--;
							if (vidas == 0) {
								cuerpoAhorcado(vidas);
								System.out.println();
								System.out.println(
										"\u001B[31m[DERROTA]\u001B[0m Se agotaron las vidas y finalmente... quedó ahorcado, has perdido la partida.");
								System.out.println();
								muerte();
							}
						}
					}
					// Permite resolver, revisa si la palabra coincide y afecta en función de si se
					// acierta o no.
				} else if (opcionMenu == 2) {
					System.out.println("Introduce la palabra: ");
					intento = let.nextLine();

					if (intento.equalsIgnoreCase(acierto)) {
						System.out.println();
						System.out.println();
						System.out.println(
								"\u001B[32m[VICTORIA]\u001B[0m ¡Enhorabuena! Has acertado la palabra y te han sobrado "
										+ vidas + " vidas.");
						vidas = 0;
					} else {
						System.out.println(
								"\u001B[31m[DERROTA]\u001B[0m Te equivocaste de palabra, has perdido la partida. Tenías "
										+ vidas + " vidas restantes.");
						vidas = 0;
						cuerpoAhorcado(vidas);
						System.out.print("La palabra era: " + acierto);
						System.out.println();
						muerte();
						System.out.println();

					}

					// Permite rendirse.
				} else if (opcionMenu == 3) {
					System.out.println("\u001B[31m[DERROTA]\u001B[0m Hahaha, ¡perdedor!");
					muerte();
					vidas = 0;
				}

				// Llama al método "comprobarAciertos".
				if (comprobarAciertos(arrayAciertos, palabra)) {
					System.out.println();
					System.out.println(
							"\u001B[32m[VICTORIA]\u001B[0m ¡Enhorabuena! Has acertado la palabra y te han sobrado "
									+ vidas + " vidas.");
					vidas = 0;
				}

			} catch (InputMismatchException e) {
				System.out.println(
						"\u001B[31m[ERROR]\u001B[0m Debes ingresar un número entre 1 y 3. Inténtalo de nuevo.");
				sc.nextLine(); // Limpiar el búfer del scanner
			}
		}
	}

	// Método "comprobarAciertos" - Comprueba si se ha acertado comparando el array
	// original y el que un principio estaba rellenado con guiones bajos.
	public static boolean comprobarAciertos(String[] arrayAciertos, String[] palabra) {
		for (int i = 0; i < palabra.length; i++) {
			if (arrayAciertos[i] != palabra[i]) {
				return false;
			}
		}
		return true;
	}

	// Método "cuerpoAhorcado" - Permite imprimir las distintas fases del muñeco del
	// ahorcado, está recogido en un array.
	public static void cuerpoAhorcado(int vidas) {
		String ahorcado[] = new String[7];
		System.out.println();

		ahorcado[6] = "  +---+\r\n" + "  |   |\r\n" + "      |\r\n" + "      |\r\n" + "      |\r\n" + "      |\r\n"
				+ "=========";

		ahorcado[5] = "  +---+\r\n" + "  |   |\r\n" + "  O   |\r\n" + "      |\r\n" + "      |\r\n" + "      |\r\n"
				+ "=========";

		ahorcado[4] = "  +---+\r\n" + "  |   |\r\n" + "  O   |\r\n" + "  |   |\r\n" + "      |\r\n" + "      |\r\n"
				+ "=========";

		ahorcado[3] = "  +---+\r\n" + "  |   |\r\n" + "  O   |\r\n" + "  |-. |\r\n" + "      |\r\n" + "      |\r\n"
				+ "=========";

		ahorcado[2] = "  +---+\r\n" + "  |   |\r\n" + "  O   |\r\n" + ".-|-. |\r\n" + "      |\r\n" + "      |\r\n"
				+ "=========";

		ahorcado[1] = "  +---+\r\n" + "  |   |\r\n" + "  O   |\r\n" + ".-|-. |\r\n" + " (    |\r\n" + "      |\r\n"
				+ "=========";

		ahorcado[0] = "  +---+\r\n" + "  |   |\r\n" + "  O   |\r\n" + ".-|-. |\r\n" + " ( )  |\r\n" + "      |\r\n"
				+ "=========";

		System.out.println(ahorcado[vidas]);
	}

	// Método "vidasAhorcado" - Permite imprimir las vidas restantes cuando es
	// necesario. Usa un array con length "vidas" para limitar el for a la cantidad
	// de vidas que recoge cuando se llama al método.
	public static void vidasAhorcado(int vidas) {
		String salud[] = new String[vidas];
		for (int i = 0; i < salud.length; i++) {
			System.out.print("\u001B[31m♥\u001B[0m ");
		}
	}

	// Método "muerte" - Imprime la cara de un muerto cuando detecta una derrota.
	public static void muerte() {
		System.out.println("		⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣤⣶⣶⣶⣶⣶⣶⣦⣤⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀");
		System.out.println("		⠀⠀⠀⠀⠀⠀⣠⣴⣾⡿⠟⠛⠉⠁⠀⠀⠀⠉⠙⠻⢿⣿⣶⣄⠀⠀⠀⠀⠀⠀");
		System.out.println("		⠀⠀⠀⠀⣠⣾⣿⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠻⣿⣷⣄⠀⠀⠀⠀");
		System.out.println("		⠀⠀⢀⣴⣿⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⣿⣧⡀⠀⠀");
		System.out.println("		⠀⣰⣿⡿⠃⠀⠀⠀⠀⠀⢠⡆⠀⠀⠀⠀⠀⠀⠀⣦⠀⠀⠀⠀⠀⠘⣿⣿⡀⠀");
		System.out.println("		⢠⣿⡟⠀⠀⠀⠀⢀⣤⣤⣾⣧⣀⣀⠀⠀⣀⣤⣴⣿⣄⣀⠀⠀⠀⠀⠸⣿⣷⠀");
		System.out.println("		⣸⣿⠃⠀⠀⠀⠀⠀⠀⠀⣿⠁⠀⠀⠀⠀⠀⠀⢹⡏⠀⠀⠀⠀⠀⠀⠀⢻⣿⡄");
		System.out.println("		⣿⣿⠀⠀⠀⢀⣀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠘⠁⠀⠀⠀⠀⢰⣦⡀⠸⣿⣇");
		System.out.println("		⢹⣿⠀⠀⣴⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢈⡟⠇⠀⣿⣿");
		System.out.println("		⢸⣿⡆⠀⠁⠘⢷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡞⠀⠀⠀⣼⣿");
		System.out.println("		⢸⣿⣇⠀⠀⠀⠈⢷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⠏⠀⠀⠀⢠⣿⡏");
		System.out.println("		⠀⢿⣿⡄⠀⠀⠀⠀⠙⢦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡞⠁⠀⠀⠀⣰⣿⡿⠀");
		System.out.println("		⠀⠈⢿⣷⡀⠀⠀⠀⠀⠀⠙⠳⢶⣤⣤⣤⣤⣴⣶⠟⣏⢹⡀⠀⢀⣼⣿⡿⠁⠀");
		System.out.println("		⠀⠀⠀⠻⣿⣦⣀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠉⠀⢸⠀⢸⠀⣇⣴⣿⡿⠋⠀⠀⠀");
		System.out.println("		⠀⠀⠀⠀⠈⠙⠿⣿⣶⣦⣤⣀⠀⠀⠀⠀⠀⠀⢸⣆⣈⣼⣿⡟⠋⠀⠀⠀⠀⠀");
		System.out.println("		⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠛⠿⠿⠿⠿⣿⣿⠿⠿⠿⠛⠋⠁⠀⠀⠀");
		System.out.println();
		System.out.println();
	}
}
