package com.mino.smartcheck

import com.mino.smartcheck.config.SmartCheckProperties
import com.mino.smartcheck.data.LeccionRepository
import com.mino.smartcheck.data.PuntajeRepository
import com.mino.smartcheck.data.UsuarioRepository
import com.mino.smartcheck.model.Leccion
import com.mino.smartcheck.model.Puntaje
import com.mino.smartcheck.model.Usuario
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.ApplicationListener
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component
import java.io.File
import java.io.IOException
import java.io.InputStream


@SpringBootApplication
@EnableConfigurationProperties(SmartCheckProperties::class)
class OrtogramaApplication

fun main(args: Array<String>) {
	SpringApplication.run(OrtogramaApplication::class.java, *args)
}

@Component
class LoadSampleData
	@Autowired constructor(
			val usuarioRepository: UsuarioRepository,
			val leccionRepository: LeccionRepository,
			val puntajeRepository: PuntajeRepository)
	: ApplicationListener<ApplicationReadyEvent>
{
	val adminUsername = "carlos.montoya.rdgz@gmail.com"

	override fun onApplicationEvent(event: ApplicationReadyEvent) {
		if (!usuarioRepository.existsByUsername(adminUsername)) {
			usuarioRepository.save(Usuario().apply {
				username = adminUsername
				password = "sipirili"
			})
		}
		if (leccionRepository.count() == 0L) {
			leccionRepository.saveAll(ArrayList<Leccion>().apply {
				add(Leccion().apply {
					clave = "puntuacion"
					contenido = Contenidos.puntuacion
				})
				add(Leccion().apply {
					clave = "acentuacion"
					contenido = Contenidos.acentuacion
				})
				add(Leccion().apply {
					clave = "letras"
					contenido = Contenidos.letras
				})
			})
		}
		if (puntajeRepository.count() == 0L) {
			puntajeRepository.save(Puntaje().apply {
				leccion = leccionRepository
						.findFirstByClave("puntuacion")
						.orElseThrow { IOException() }
				usuario = "carlos.montoya.rdgz@gmail.com"
				puntaje = 3
			})
			puntajeRepository.save(Puntaje().apply {
				leccion = leccionRepository
						.findFirstByClave("acentuacion")
						.orElseThrow { IOException() }
				usuario = "carlos.montoya.rdgz@gmail.com"
				puntaje = 1
			})
			puntajeRepository.save(Puntaje().apply {
				leccion = leccionRepository
						.findFirstByClave("letras")
						.orElseThrow { IOException() }
				usuario = "carlos.montoya.rdgz@gmail.com"
				puntaje = 1
			})
		}
	}
}

object Contenidos
{
	const val acentuacion = "<h2>Acentuación</h2>\n" +
			"<h3>1. Las palabras según su acento</h3>\n" +
			"<p>Las palabras, en español, tienen una sílaba llamada tónica, que es la que, al pronunciarla, suena más\n" +
			"  fuerte. Las otras se llaman sílabas átonas, porque su intensidad de voz es menor que la tónica. En algunas\n" +
			"  ocasiones, la vocal de la sílaba tónica se marca mediante un signo ortográfico que se coloca en la parte\n" +
			"  superior de ésta (´). Este signo se denomina tilde o acento gráfico. Atendiendo a su sílaba tónica, las\n" +
			"  palabras, en español, pueden clasificarse en:</p>\n" +
			"<p>AGUDAS:<br>\n" +
			"  Son aquellas palabras cuyo golpe de voz recae sobre la última sílaba.<br>\n" +
			"  <em><span style=\"color: #44878a;\">ca<b>jón</b>, par<b>tir,</b> a<b>tril,</b> so<b>fá</b></span></em></p>\n" +
			"<p>LLANAS O GRAVES:<br>\n" +
			"  Son palabras de más de una sílaba. El golpe de voz recae sobre la penúltima sílaba.<br>\n" +
			"  <em><span style=\"color: #44878a;\">ár<b>bol</b>, ca<b>mi</b>sa, <b>si</b>lla, <b>lá</b>piz</span></em></p>\n" +
			"<p>ESDRÚJULAS Y SOBREESDRÚJULAS:<br>\n" +
			"  Son aquellas palabras de más de dos sílabas. El golpe de voz recae sobre la antepenúltima sílaba\n" +
			"  (esdrújulas) o antes de la antepenúltima (sobreesdrújula).<br>\n" +
			"  cántaro, pájaro, íntimo, cándido, cámbiaselo<br>\n" +
			"  <em><span style=\"color: #44878a;\"><b>cán</b>taro, <b>pá</b>jaro, <b>ín</b>timo,<b> cán</b>dido, <b>cám</b>biaselo</span></em>\n" +
			"</p>\n" +
			"<h3></h3>\n" +
			"<h3>2. Reglas generales de acentuación gráfica (Empleo de la tilde)</h3>\n" +
			"<p>Llevan acento ortográfico (o tilde [´])</p>\n" +
			"<p>1ª Regla:</p>\n" +
			"<p>Llevarán tilde las palabras agudas terminadas en vocal (a,e,i,o,u) y en las consonantes -n y -s.<br>\n" +
			"  <em><span style=\"color: #44878a;\">A<b>nís</b> – pa<b>pá </b>– ca<b>jón</b> – sar<b>tén</b> – mani<b>quí</b> – ve<b>nís.</b></span></em>\n" +
			"</p>\n" +
			"<p>2ª Regla:<br>\n" +
			"  Llevarán tilde las palabras Ilanas que terminen en consonante, que no sea ni -n, ni -s.<br>\n" +
			"  <em><span style=\"color: #44878a;\"><b>Ú</b>til – <b>lá</b>piz – <b>ál</b>bum – <b>al</b>cázar.</span></em>\n" +
			"</p>\n" +
			"<p>3ª Regla:<br>\n" +
			"  Llevarán tilde todas las palabras esdrújulas y sobreesdrújulas.<br>\n" +
			"  <em><span style=\"color: #44878a;\"><b>Cán</b>dido – es<b>drú</b>jula – <b>cuén</b>taselo – cele<b>bé</b>rrimo.</span></em>\n" +
			"</p>\n" +
			"<h3>3. Reglas particulares de la acentuación gráfica</h3>\n" +
			"<h5>La tilde diacrítica</h5>\n" +
			"<p>Hay palabras que, atendiendo a las reglas generales de acentuación gráfica, no deberían llevar tilde. Sin\n" +
			"  embargo, algunas palabras admiten lo que se llama la tilde diacrítica, cuya función es la de evitar la\n" +
			"  confusión en la lengua escrita entre dos palabras que se escriben de la misma forma.</p>\n" +
			"<table class=\"table table-striped\">\n" +
			"  <tbody>\n" +
			"  <tr>\n" +
			"    <td>\n" +
			"      <p align=\"center\"><b>NO LLEVA TILDE</b></p>\n" +
			"    </td>\n" +
			"    <td>\n" +
			"      <p align=\"center\"><b>LLEVA TILDE DIACRÍTICA</b></p>\n" +
			"    </td>\n" +
			"  </tr>\n" +
			"  <tr>\n" +
			"    <td><b>El</b>&nbsp;(artículo)<br>\n" +
			"      <span style=\"color: #44878a;\"><i>El sol reluce</i></span></td>\n" +
			"    <td><b>Él&nbsp;</b>(pronombre personal)<br>\n" +
			"      <span style=\"color: #44878a;\"><i>Él sabe tocar la flauta</i></span></td>\n" +
			"  </tr>\n" +
			"  <tr>\n" +
			"    <td><b>De&nbsp;</b>(preposición)<br>\n" +
			"      <span style=\"color: #44878a;\"><i>Iros de aquí.</i></span></td>\n" +
			"    <td><b>Dé</b>&nbsp;(verbo dar)<br>\n" +
			"      <span style=\"color: #44878a;\"><i>Quiero que me dé esto.</i></span></td>\n" +
			"  </tr>\n" +
			"  <tr>\n" +
			"    <td><b>Se</b>&nbsp;(pronombre)<br>\n" +
			"      <span style=\"color: #44878a;\"><i>Se acabó.</i></span></td>\n" +
			"    <td>&nbsp;<b>Sé</b>&nbsp;(verbo saber y verbo ser)<br>\n" +
			"      <span style=\"color: #44878a;\"><i>Sé tú mismo;</i>&nbsp;<i>sé que lo harás.</i></span></td>\n" +
			"  </tr>\n" +
			"  <tr>\n" +
			"    <td><b>Tu – mi</b>&nbsp;(determinantes posesivos)<br>\n" +
			"      <span style=\"color: #44878a;\"><i>Tu padre está ahí.&nbsp;</i></span><br>\n" +
			"      <span style=\"color: #44878a;\"> <i>Mi juguete está estropeado</i></span></td>\n" +
			"    <td><b>Tú – mí</b>&nbsp;(pronombres personales)<br>\n" +
			"      <span style=\"color: #44878a;\"><i>Tú no debes ir allí.&nbsp;</i></span><br>\n" +
			"      <span style=\"color: #44878a;\"> <i>Eso es para mí</i>.</span></td>\n" +
			"  </tr>\n" +
			"  <tr>\n" +
			"    <td><b>Si&nbsp;</b>(condicional)<p></p>\n" +
			"      <p><span style=\"color: #44878a;\"><i>Si no llueve, iré</i></span></p></td>\n" +
			"    <td><b>Sí</b>&nbsp;(afirmación y pronombre)<br>\n" +
			"      <span style=\"color: #44878a;\"><i>Creo que sí que iré.&nbsp;</i></span><br>\n" +
			"      <span style=\"color: #44878a;\"> <i>Lo tomó para sí.</i></span></td>\n" +
			"  </tr>\n" +
			"  <tr>\n" +
			"    <td><b>Mas</b>&nbsp;(conjunción)<br>\n" +
			"      <span style=\"color: #44878a;\"><i>Le llamé, mas no contestó.</i></span></td>\n" +
			"    <td><b>Más</b>&nbsp;(adverbio)<br>\n" +
			"      <span style=\"color: #44878a;\"><i>No quiero más</i>.</span></td>\n" +
			"  </tr>\n" +
			"  <tr>\n" +
			"    <td><b>Te</b>&nbsp;(pronombre)<br>\n" +
			"      <span style=\"color: #44878a;\"><i>Te mandaré más trabajo.</i></span><i>&nbsp;</i></td>\n" +
			"    <td><b>Té</b>&nbsp;(sustantivo – infusión)<i>&nbsp;</i><br>\n" +
			"      <span style=\"color: #44878a;\"><i>Tómate ya ese té.</i></span><i>&nbsp;</i></td>\n" +
			"  </tr>\n" +
			"  </tbody>\n" +
			"</table>\n" +
			"<h5>Acentuación de los diptongos y los triptongos</h5>\n" +
			"<p>Los diptongos y triptongos llevarán tilde cuando sigan la regla general de acentuación. La tilde se\n" +
			"  colocará sobre la vocal abierta del diptongo o triptongo.<br>\n" +
			"  <em><span style=\"color: #44878a;\">Reunión – exceptuéis – tráigamelo – efectuáis.</span></em></p>\n" +
			"<h5>Acentuación de los hiatos</h5>\n" +
			"<p>Cuando el hiato es el resultado de la destrucción de un diptongo, es decir, cuando hay dos vocales junta\n" +
			"  pertenecientes a sílabas diferentes, y una de ellas es una -i o una -u, se pondrá la tilde sobre la i o la\n" +
			"  u, aunque no siga la regla general.</p>\n" +
			"<p><em><span style=\"color: #44878a;\">Subían – oíamos – incluía – raíz</span></em></p>\n" +
			"<h5>Acentuación de los exclamativos e interrogativos</h5>\n" +
			"<p>Las palabras que, quien, cual, cuando, cuanto, donde y como llevarán tilde siempre que sean utilizados\n" +
			"  exclamativos y como interrogativos.<br>\n" +
			"  <em><span style=\"color: #44878a;\">¿Cómo sabes todo eso? ¿Quién te lo ha dicho? ¡Qué vacaciones tan divertidas! ¿Cuántas botellas quedan?</span></em>\n" +
			"</p>\n" +
			"<h5>Acentuación de las palabras compuestas</h5>\n" +
			"<ul>\n" +
			"  <li>Las palabras compuestas siguen las reglas generales de acentuación gráfica, como si se tratara de una\n" +
			"    palabra simple.\n" +
			"  </li>\n" +
			"</ul>\n" +
			"<p style=\"padding-left: 60px;\"><em><span style=\"color: #44878a;\"> decimoséptimo – ciempiés.</span></em></p>\n" +
			"<ul>\n" +
			"  <li>Se exceptúan de esta regla los adverbios en<strong> -mente</strong>, como <em><span\n" +
			"    style=\"color: #44878a;\">ágilmente, cortésmente</span></em>, QUE SÍ MANTIENEN EL ACENTO GRÁFICO de la\n" +
			"    palabra primitiva. Cuando la composición de la palabra sea mediante guión, las dos palabras simples\n" +
			"    mantendrán, si lo llevan como simples, su tilde.\n" +
			"  </li>\n" +
			"</ul>\n" +
			"<p style=\"padding-left: 60px;\"><em><span style=\"color: #44878a;\"> Físico-químico – hispano-francés</span></em>\n" +
			"</p>\n" +
			"<ul>\n" +
			"  <li><strong>Ojo</strong>: Hasta ahora, cuando uníamos a los verbos pronombres personales átonos, si el verbo\n" +
			"    llevaba tilde, se mantenía. <em><span style=\"color: #44878a;\">acercó – acercóse</span></em>. Pues bien,\n" +
			"    ahora esto se ha modificado, y los verbos seguirán siempre las reglas generales de acentuación: <em><span\n" +
			"      style=\"color: #44878a;\">acercó – acer<strong>co</strong>se</span></em>. (Sin tilde porque es llana\n" +
			"    acabada en vocal)\n" +
			"  </li>\n" +
			"</ul>\n" +
			"<ul>\n" +
			"  <li>Si el verbo no lleva tilde, cuando unimos los pronombres personales, sigue la regla general de\n" +
			"    acentuación como si se tratara de una palabra simple.\n" +
			"  </li>\n" +
			"</ul>\n" +
			"<p style=\"padding-left: 60px;\"><em><span style=\"color: #44878a;\"> di – díselo; dame – dámelo; cuenta – cuéntaselo.</span></em>\n" +
			"</p>\n" +
			"<h5>La tilde en las letras mayúsculas</h5>\n" +
			"<p>Es necesario colocar la tilde en las letras mayúsculas cuando les corresponda por regla general.<br>\n" +
			"  <em><span style=\"color: #44878a;\">Álvaro – Ávila – Álava</span></em>\n" +
			"</p>\n"
	const val puntuacion = "<h2>Signos y reglas de puntuación</h2>\n" +
			"<p>Los signos ortográficos nos permiten indicar en la escritura la pronunciación de las\n" +
			"  palabras (tilde, diéresis), la entonación (exclamación, interrogación), las pausas de la frase (punto, coma,\n" +
			"  punto y coma…), etc. haciendo más fácil y ágil la lectura de los textos y, por tanto, su comprensión. Toda\n" +
			"  <a href=\"http://www.editorial-falsaria.com/correccion-ortotipografica/\">corrección de textos</a>, pensada\n" +
			"  para<a title=\" editar\" href=\" https://www.falsaria.com/autoedicion-de-libros/\"> editar</a> o <a\n" +
			"    href=\"http://www.falsaria.com/autopublicacion-de-libros/\">autoeditar un libro</a>&nbsp;debe\n" +
			"  contar con esta idea fundamental. Una redacción no es correcta si se usan inadecuadamente los signos\n" +
			"  ortográficos, y en especial los&nbsp;signos de puntuación.</p>\n" +
			"<p>En esta leccion te contamos, de forma resumida los signos ortográficos y las reglas de puntuación\n" +
			"  y acentuación en español, tal y como los recoge la Real Academia Española. Un buen repaso para\n" +
			"  aquellos <a title=\"escritores\" href=\"http://blog.falsaria.com/tag/escritores/\">estudiantes</a> que desean\n" +
			"  mejorar su ortorgafía y gramática y, de ese modo, mejorar su escritura. Recuerda que siempre debes tener a\n" +
			"  manos el diccionarios y para más comodidad puedes usar el la web de la real academia que es rápida y siempre\n" +
			"  está actualizada.</p>\n" +
			"<p>Aunque en español existe un gran número de reglas de puntuación, aquí sólo vamos a estudiar las más básicas\n" +
			"  y generales.</p>\n" +
			"<h3>1. El punto.</h3>\n" +
			"<p><b>a. </b>El punto y seguido: termina una frase con sentido completo y separa enunciados&nbsp;que integran\n" +
			"  un párrafo. Después de un punto y seguido se continúa escribiendo en la misma línea.</p>\n" +
			"<p><b>b. </b>El punto y aparte: separa dos párrafos distintos. Después de un punto y aparte, se&nbsp;escribe\n" +
			"  en una línea distinta. La primera línea del nuevo párrafo debe tener un margen mayor que el&nbsp;resto de\n" +
			"  las líneas que lo componen.</p>\n" +
			"<p><b>c. </b>El punto final: es el que cierra un texto.</p>\n" +
			"<p>Ej.:&nbsp;<em style=\"text-align: left;\">El mar estaba embravecido aquel día. (punto y seguido) Los barcos\n" +
			"  bailaban sobre el agua sorteando las olas con dificultad. (punto y aparte) Miguel, sentado en el muelle,\n" +
			"  esperaba el regreso de su padre. (punto y seguido) Atisbaba el horizonte buscando ansioso su barco con la\n" +
			"  mirada. (punto final)</em></p>\n" +
			"<p><b>d. </b>Detrás de las abreviaturas:</p>\n" +
			"<p>Ej.: Sr., Sra., Exmo.&nbsp;<em style=\"text-align: left;\">Se suele escribir también con las siglas (C.S.I.C.\n" +
			"  = Centro Superior de Investigaciones Científicas); aunque la tendencia actual es no ponerlo cuando las\n" +
			"  siglas no se forman con la primera letra de cada una de sus palabras (AVIACO).</em></p>\n" +
			"\n" +
			"<h3>2. La coma ,</h3>\n" +
			"<p><b>a. </b>Separa las partes de una enumeración o serie dentro de una oración; incluidos miembros\n" +
			"  gramaticalmente equivalentes dentro de un mismo enunciado, a excepción de los casos en los que medie alguna\n" +
			"  de las conjunciones y, e, ni, o, u (sólo se pondrá coma delante de estas&nbsp;conjunciones para evitar\n" +
			"  confusiones):</p>\n" +
			"<p>Ej.: <em>Vimos elefantes, leones, tigres, jirafas…</em></p>\n" +
			"<p><em>Están ocupadas las habitaciones número 28, 55, 134 y 572. </em></p>\n" +
			"<p><em>Estaba preocupado por su familia, por su trabajo, por su salud. </em></p>\n" +
			"<p><em>Corre las cortinas, cierra las ventanas, apaga las luces y echa la llave.&nbsp;</em></p>\n" +
			"\n" +
			"<p><b>b. </b>Cuando se cambia el orden regular de las partes de una oración, anteponiendo elementos que\n" +
			"  suelen ir pospuestos, se coloca una coma después del bloque anticipado:</p>\n" +
			"<p>Ej.: <em>Te compraré el caramelo si me dices la verdad.</em> (Orden regular, no escribimos coma)</p>\n" +
			"<p><em>Si me dices la verdad, te compraré el caramelo.</em> (Se antepone la condicional, escribimos coma)</p>\n" +
			"<p><em>Escríbenos una carta cuando llegues.</em> (Orden regular, no escribimos coma)</p>\n" +
			"<p><em>Cuando llegues, escríbenos una carta.</em> (Se ant epone la temporal,&nbsp;escribimos coma)</p>\n" +
			"\n" +
			"<p><b>c. </b>Separa los incisos, las explicaciones que pueden aparecer en una oración:</p>\n" +
			"<p>Ej.: <em>Llegó Luis, el novio de Mónica, y nos invitó a todos a cenar.&nbsp;</em></p>\n" +
			"<p><em>Cervantes, quien es un gran <a title=\"escritor\"\n" +
			"                                      href=\"http://blog.falsaria.com/tag/escritor/\">escritor</a> español,\n" +
			"  vivió enValladolid.</em></p>\n" +
			"<p><em>Tiene sólo quince años, es decir, aún no es mayor de edad.</em></p>\n" +
			"\n" +
			"<p><b>d. </b>Para indicar la omisión de un verbo:</p>\n" +
			"<p>Ej.: <em>Juan Manuel ha comprado la casa; Marta, los muebles.</em></p>\n" +
			"<p><em>Joaquín es policía nacional; Ana, graduada social.</em></p>\n" +
			"\n" +
			"<h3>3. El punto y coma ;</h3>\n" +
			"<p><b>a. </b>Separa los elementos de una enumeración cuando se trata de expresiones complejas&nbsp;que\n" +
			"  incluyen comas o son demasiado extensos:</p>\n" +
			"<p>Ej.:<em> La chaqueta es azul; los pantalones, grises; la camisa, blanca; el&nbsp;abrigo, negro.</em></p>\n" +
			"\n" +
			"<p><b>b. </b>Separa oraciones no unidas por preposiciones cuando en ellas se ha empleado la&nbsp;coma\n" +
			"  (también se podría optar por el punto y seguido):</p>\n" +
			"<p>Ej.: <em>La situación económica de la empresa, agravada en los últimos&nbsp;tiempos, era preocupante; se\n" +
			"  imponía una acción rápida y contundente&nbsp;</em><em>si se deseaba salvar los puestos de trabajo.</em></p>\n" +
			"\n" +
			"<h3>4. Los dos puntos :</h3>\n" +
			"<p><b>a. </b>Preceden a una enumeración:</p>\n" +
			"<p>Ej.:<em> Tres son las provincias de Aragón: Huesca, Zaragoza y Teruel.</em></p>\n" +
			"\n" +
			"<p><b>b. </b>Preceden a las citas textuales:</p>\n" +
			"<p>Ej.: <em>Las palabras del médico fueron: “Reposo y una alimentación&nbsp;equilibrada”.</em></p>\n" +
			"\n" +
			"<p><b>c. </b>Detrás de las fórmulas de saludo en las cartas y documentos. La palabra que sigue a los dos\n" +
			"  puntos se escribe con mayúscula y en un englón aparte:</p>\n" +
			"<p>Ej.: <em>Querido amigo:</em></p>\n" +
			"<p><em>Te escribo para…</em></p>\n" +
			"\n" +
			"<p><b>d. </b>Antes de la frase en la que se recogen las conclusiones, causas, consecuencias,\n" +
			"  etc.,&nbsp;o se resume lo expuesto con anterioridad:</p>\n" +
			"<p>Ej.:<em> Suspendieron todos los preparativos, anularon las invitaciones, se lo comunicaron a sus padres y a\n" +
			"  los amigos más cercanos: no se casarían ese año.</em></p>\n" +
			"\n" +
			"<h3>5. Los puntos suspensivos …</h3>\n" +
			"<p><b>a. </b>Al final de enumeraciones o enunciados incompletos:</p>\n" +
			"<p><em>Ej.: Puedes hacer lo que te apetezca: leer, ver la t elevisión, escuchar&nbsp;música…</em></p>\n" +
			"<p><em>Fue todo muy desagradable… No quiero seguir hablando de ello</em></p>\n" +
			"\n" +
			"<p><b>b. </b>Para expresar duda, temor, emoción, etc.:</p>\n" +
			"<p>Ej.: <em>Iré, no iré… Debo decidirme pronto.</em></p>\n" +
			"\n" +
			"<p><b>c. </b>Se usan entr e corchetes para indicar la omisión de parte de un texto\n" +
			"  copiado&nbsp;literalmente:</p>\n" +
			"<p>Ej.: <em>Al salir el marido le dijo la falsa mujer a la buena esposa que, […],&nbsp;buscaría a algún hombre\n" +
			"  que supiera hacer algún encantamiento con&nbsp;</em><em>que su marido perdiera la mala voluntad que le\n" +
			"  estaba mostrando.</em></p>\n" +
			"\n" +
			"<p><span\n" +
			">6. Los signos de interrogación ¿ ? y exclamación ¡ !</span>\n" +
			"</p>\n" +
			"<p>Notad que en español hay dos, uno que inicia la frase (¡ ¿) y otro que la cierra (! ?):</p>\n" +
			"<p>Ej.:<em> ¿Comiste ayer en casa?</em></p>\n" +
			"<p><em>¡Qué magnífica pintura!</em></p>\n" +
			"<p><em>Si no responde al teléfono, ¿qué hacemos?</em></p>\n" +
			"<p>Estos signos pueden aparecer seguidos por coma, punto y coma o puntos suspensivos, pero&nbsp;nunca de punto\n" +
			"  y deben colocarse donde &nbsp;empiece la exclamación o la pregunta:</p>\n" +
			"<p>Ej.: <em>Pero tú, ¿cuántos años tienes tú?</em></p>\n" +
			"\n" +
			"<h3>7. Los paréntesis ( )</h3>\n" +
			"<p><b>a. </b>Introducen aclaraciones:</p>\n" +
			"<p>Ej.: <em>El abuelo de Alberto (en su juventud fue un brillante cirujano) parecía&nbsp;una estatua sentado\n" +
			"  en aquel sillón.</em></p>\n" +
			"<p><em>El año de su nacimiento (1616) es el mismo en el que murió Cervantes.</em></p>\n" +
			"<h3>8. Los corchetes [ ]</h3>\n" +
			"<p><b>a. </b>Enmarcan los incisos dentro de un período que ya va entre paréntesis:</p>\n" +
			"<p>Ej.: <em>Una de las últimas novelas que publicó Benito Pérez Galdós (algunos&nbsp;</em><em>estudiosos\n" +
			"  consideran su obra Fortunata y Jacinta [1886-87] la mejor&nbsp;</em><em>novela española del siglo XIX) fue\n" +
			"  El caballero encantado (1909).</em></p>\n" +
			"\n" +
			"<p><b>b. </b>Enmarcan los puntos suspensivos para indicar la omisión de parte de un\n" +
			"  texto&nbsp;copiado literalmente.</p>\n" +
			"<p>Ej.: <em>Le sonreí para decírselo; pero después pensé que él no pudo ver mi&nbsp;sonrisa […] por lo negra\n" +
			"  que estaba la noche.</em></p>\n" +
			"\n" +
			"<h3>9. La raya —</h3>\n" +
			"<p><b>a. </b>Introduce aclaraciones (como el paréntesis):</p>\n" +
			"<p>Ej.: <em>Esperaba a Emilio —un gran amigo—. Lamentablemente, no vino.</em></p>\n" +
			"\n" +
			"<p><b>b. </b>Señala cada una de las intervenciones en un diálogo:</p>\n" +
			"<p>Ej.: <em>—¿Deberíamos hablar con él? —preguntó Juan—. Es el único que no&nbsp;lo sabe.</em></p>\n" +
			"<p><em>—Sí —respondió la secretaria—, pero no podemos decirle toda la&nbsp;verdad.</em></p>\n" +
			"\n" +
			"<h3>10. El guión –</h3>\n" +
			"<p><b>a. </b>Se utiliza cuando es necesario hacer divisiones dentro de una palabra o para unir&nbsp;dos\n" +
			"  números y no se escribe entre espacios en blanco:</p>\n" +
			"<p>Ej.: <em>luso-japonés, teórico-práctico&nbsp;</em></p>\n" +
			"<p><em>1936-37, 1567-1572</em></p>\n" +
			"\n" +
			"<h3>11. Las comillas “ ” « »</h3>\n" +
			"<p><b>a. </b>Para reproducir citas textuales:</p>\n" +
			"<p>Ej.: <em>Sus palabras fueron: “Por favor, el pasaporte”.</em></p>\n" +
			"\n" +
			"<p><b>b. </b>Para citar títulos de artículos, poemas, cuadros, lugares…</p>\n" +
			"<p>Ej.: <em>Nos leyó en voz alta el poema “Romance sonámbulo” de García&nbsp;Lorca.</em></p>\n" +
			"\n" +
			"<h3>12. La diéresis ¨</h3>\n" +
			"<p><b>a. </b>Se sitúa sobre la vocal u en las combinaciones gue y gui para indicar que la vocal&nbsp;debe\n" +
			"  pronunciarse:</p>\n" +
			"<p>Ej.: <em>cigüeña, pingüino</em></p>\n" +
			"\n" +
			"<p><b>b. </b>Se sitúa sobre la primera vocal de un diptongo en textos poéticos para indicar que&nbsp;tal\n" +
			"  diptongo no existe y que el verso cuenta con una sílaba más:</p>\n" +
			"<p>Ej.: <em>El dulce murmurar deste rüido</em></p>\n" +
			"\n" +
			"<h3>13. El asterisco *</h3>\n" +
			"<p><b>a. </b>Como signo de llamada de nota al margen o a pie de página dentro de un texto; en&nbsp;ocasiones\n" +
			"  puede aparecer encerrado entre paréntesis:</p>\n" +
			"<p>Ej.: <em>La novela fue escrita por García Márquez* un año antes de que se le&nbsp;concediera el <a\n" +
			"  href=\"https://es.wikipedia.org/wiki/Anexo:Ganadores_del_Premio_Nobel_de_Literatura\">Nobel de Literatura.</a></em>\n" +
			"</p>\n" +
			"<p></p>\n"
	const val letras = "<h2>Uso de B y V</h2>\n" +
			"<p>\n" +
			"  La letra B (be) tiene el mismo sonido /b/ que la letra V (uve), lo que representa una dificultad para escribir correctamente en español.\n" +
			"</p>\n" +
			"<p>\n" +
			"  Además, hay palabras que se pronuncian igual pero al escribirlas con B o con V cambia totalmente su significado.\n" +
			"</p>\n" +
			"<ul>\n" +
			"  <li><b>Cabo</b> (lengua de tierra que penetra en el mar; empleo militar).</li>\n" +
			"  <li><b>Cavo</b> (del verbo cavar).</li>\n" +
			"</ul>\n" +
			"<ul>\n" +
			"  <li><b>Baca</b> (portaequipajes que se coloca sobre el techo del automóvil).</li>\n" +
			"  <li><b>Vaca</b> (hembra del toro).</li>\n" +
			"</ul>\n" +
			"<p>\n" +
			"  Por tanto, es muy importante conocer las Reglas Ortográficas de la B y también el significado de las palabras.\n" +
			"</p>\n" +
			"<h3>Se escriben con B</h3>\n" +
			"<ul>\n" +
			"  <li>el sonido final -bir de los infinitivos y todas las formas de estos verbos (excepto hervir, servir y vivir y sus compuestos)</li>\n" +
			"  <li>los infinitivos y todas las formas de los verbos beber y deber</li>\n" +
			"  <li>los infinitivos y formas verbales de caber, haber y saber</li>\n" +
			"  <li>las terminaciones -ba, -bas, -bamos, -bais y -ban</li>\n" +
			"  <li>el pretérito imperfecto de indicativo del verbo ir</li>\n" +
			"  <li>los vocablos que empiezan con el sonido bibl- (biblioteca, Biblia, bibliografía, etc.) o con la sílaba bu-, bur- y bus- (burro, burla, buscar)</li>\n" +
			"  <li>las terminaciones -bundo, -bunda y -bilidad (excepto movilidad y civilidad): nauseabundo, furibunda, amabilidad; las palabras en las que dicho sonido precede a otra consonante: amable, brazo, abdicación, abnegación, obstruir, obtener, obvio. Esta regla incluye las sílabas bla, ble, bli, blo, blu y bra, bre, bri, bro, bru</li>\n" +
			"  <li>los prefijos bi-, bis-, biz-, que significan ‘dos’ o ‘dos veces’: bilingüe, bisiesto, biznieto</li>\n" +
			"  <li>los prefijos bien- y bene- (que significan ‘bien’): bienintencionado, beneplácito, benévolo, beneficio, y los compuestos y derivados de voces que llevan esta letra.</li>\n" +
			"</ul>\n" +
			"<h3>Se escriben con V</h3>\n" +
			"<ul>\n" +
			"  <li>el presente de indicativo, de imperativo y de subjuntivo del verbo ir (voy, vas; ve, vaya; vaya, vayamos) y el pretérito indefinido, el pretérito imperfecto y el futuro de subjuntivo de los verbos estar, andar y tener y sus compuestos (estuve, estuviera, estuviere; anduve, anduviera, anduviese; tuve, tuviera, tuviese)</li>\n" +
			"  <li>las terminaciones de adjetivos -ava, -ave, -avo; -eva, -eve, -evo e -iva, -ivo: octava, suave, bravo, nueva, leve, decisivo (se exceptúan árabe, sílaba, que no es adjetivo, y sus compuestos: mozárabe, trisílaba, etc.)</li>\n" +
			"  <li>después de d: advertencia, advenedizo, adversario</li>\n" +
			"  <li>los prefijos vice-, villa-, villar-: vicealmirante, Villalobos, etc.</li>\n" +
			"  <li>las terminaciones -viro, -vira, -ívoro e -ívora (excepto víbora): Elvira, carnívoro, herbívora</li>\n" +
			"  <li>las terminaciones -servar y -versar de los verbos conservar, observar, reservar y conversar</li>\n" +
			"  <li>las formas de los verbos que no tienen ni v ni b en su infinitivo: tuve, estuve, anduvieron, vayamos (se exceptúan las terminaciones –ba, -bas, -bamos, -bais y -ban del pretérito imperfecto de indicativo)</li>\n" +
			"  <li>las palabras en las que este sonido aparece detrás de las letras b, d y n: obvio, adverso, invierno, y los compuestos y derivados de palabras que llevan esa letra: prevenir de venir, revuelta de vuelta, etc.</li>\n" +
			"</ul>\n" +
			"<h2>Uso de las letras C, S y Z</h2>\n" +
			"<p>\n" +
			"  La letra C, a menudo se confunde con el sonido S, pero también con el fonema K. En esta ocasión, estudiaremos el primer caso.\n" +
			"  Si deseas información acerca del segundo uso, te recomendamos buscar en nuestra página: reglas de uso de C, K y Q.\n" +
			"</p>\n" +
			"<h3>Se escriben con C</h3>\n" +
			"<ul>\n" +
			"  <li>Los verbos terminados en cir y ducir. Excepción: asir.</li>\n" +
			"  <li>Las palabras terminadas en ancia, ancio, encía. Excepciones: ansia, Hortensia.</li>\n" +
			"  <li>Las palabras terminadas en ción, afines a to, tor, dar.</li>\n" +
			"  <li>Los sufijos cida, cido, cidio.</li>\n" +
			"  <li>Las palabras terminadas en cimiento.</li>\n" +
			"  <li>Los verbos terminados en cer. Excepciones: toser, coser, ser.</li>\n" +
			"  <li>Los verbos terminados en ciar. Excepciones: lisiar, ansiar, extasiar, anestesiar.</li>\n" +
			"  <li>Las palabras terminadas en acia, icia, icie, icio. Excepciones: Dionisio, gimnasio, Asia, anastasia, alisio, eutanasia.</li>\n" +
			"  <li>Los verbos terminados en zar ante la vocal e cambian a c.</li>\n" +
			"</ul>\n" +
			"<h3>Se escriben con S</h3>\n" +
			"<ul>\n" +
			"  <li>Las palabras que terminan en ense, referente a los gentilicios.</li>\n" +
			"  <li>Las terminaciones sivo, siva.</li>\n" +
			"  <li>Las palabras terminadas en sión, cuando proceden de palabras terminadas en so, sor, sivo.</li>\n" +
			"  <li>Las palabras terminadas en los superlativos isimo, isima.</li>\n" +
			"  <li>Las palabras terminadas en oso, osa.</li>\n" +
			"  <li>Las palabras terminadas en ismo.</li>\n" +
			"  <li>Las palabras terminadas en esca, esco.</li>\n" +
			"  <li>Las terminaciones esta, esto, ista.</li>\n" +
			"  <li>La terminación se de todos los verbos del pretérito imperfecto, del modo subjuntivo.</li>\n" +
			"  <li>Las terminaciones ersa, erse, erso.</li>\n" +
			"</ul>\n" +
			"<h3>Se escriben con Z</h3>\n" +
			"<ul>\n" +
			"  <li>Las palabras terminadas en anza/o y azgo. Excepciones: gansa/o, mansa/o.</li>\n" +
			"  <li>Las terminaciones ez, eza, az, oz, de los nombres abstractos.</li>\n" +
			"  <li>Las terminaciones azo, aza que denotan aumento, golpe.</li>\n" +
			"  <li>Las terminaciones iz, ez, oz, az, de los nombres patronímicos.</li>\n" +
			"  <li>Las terminaciones zuela, zuelo, que denotan disminución o desprecio.</li>\n" +
			"  <li>Algunos verbos en infinitivo terminados en zar y sus conjugaciones delante de las vocales a, o.</li>\n" +
			"  <li>Las palabras terminadas en izo, iza.</li>\n" +
			"</ul>\n" +
			"<h2>Uso de la Q y K.</h2>\n" +
			"<p>\n" +
			"  Las letras K y Q,  se confunden es su uso, cuando representan el sonido K. Por ejemplo,\n" +
			"  quiero y kilo, suenan como una K. Te presentamos las normas de uso de estas tres letras,\n" +
			"  para que puedas mejorar tus escritos y expresar tus ideas con una adecuada ortografía.\n" +
			"</p>\n" +
			"<h3>Uso de la Q</h3>\n" +
			"<ul>\n" +
			"  <li>Se emplea agrupada con la u (muda) ante las vocales e, i: quedar, quirófano.</li>\n" +
			"  <li>Muchas palabras derivadas de otras que llevan c frente a la a, o, u, se escriben con q cuando debe sonar fuerte frente a la e o la i: atacar -> ataque, saco → saquito.</li>\n" +
			"  <li>Sólo se pronuncia la u en algunas palabras latinas: quórum, quid.</li>\n" +
			"</ul>\n" +
			"<h3>Uso de la K</h3>\n" +
			"<ul>\n" +
			"  <li>Se usa sólo en palabras procedentes de otras lenguas en las que se ha intentado respetar la ortografía original: kamikaze, karate, karaoke, karma, kayak, kendo, ketchup, kit, kitsch, kiwi, koala, kung-fu, búnker, taekwondo.</li>\n" +
			"  <li>En la raíz:  kilo- = ‘mil’ → kilogramo, kilómetro, kilovatio, kilobytes.</li>\n" +
			"  <li>Algunas palabras se pueden escribir con k, con c o con q: biquini - bikini, caqui - kaki, kermés - quermés, quimono - kimono, quiosco - kiosco, curdo - kurdo, vodka - vodca</li>\n" +
			"</ul>\n" +
			"<h2>Uso de la G y J.</h2>\n" +
			"<p>\n" +
			"  Tanto el uso de la g, como el de la j, no ofrece dificultad cuando precede a\n" +
			"  las vocales a, o y u. Las dudas surgen cuandoel sonido g/j precede a e o a i.\n" +
			"</p>\n" +
			"<h3>Uso de la G:</h3>\n" +
			"<ul>\n" +
			"  <li>el prefijo geo- de las palabras compuestas: geografía, geometría, geología, etc.; </li>\n" +
			"  <li>la terminación -gen de los nombres: origen, margen, aborigen, etc.;</li>\n" +
			"  <li>las terminaciones -gélico, -genario, -géneo, -génico, -genio, -génito, -gesimal, -gésimo, -gético y sus femeninos y plurales: angélico, sexagenario, homogéneo, fotogénico, ingenio, primogénito, cuadragesimal, vigésimo, apologético;</li>\n" +
			"  <li>las terminaciones -gia, -gio, -gión, -gional, -gionario, -gioso y -gírico: magia, regio, religión, regional, legionario, prodigioso, panegírico, etc.;</li>\n" +
			"  <li>las terminaciones -ger y -gir de los infinitivos: proteger, escoger, recoger, fingir, corregir, dirigir, etc. Menos tejer, crujir y sus derivados.</li>\n" +
			"  <li>Los tiempos de los verbos que llevan esta letra en el infinitivo.</li>\n" +
			"</ul>\n" +
			"<p>\n" +
			"  Además, es preciso recordar que la g con la e y la i tiene sonido gutural fuerte (como en gente o en gigante);\n" +
			"  para representar ese mismo sonido suave, se coloca una u muda entre la g y la e o\n" +
			"  la i: guerra, guiso…; cuando esa u intermedia suena,\n" +
			"  se escribe con diéresis, como en pingüino.\n" +
			"</p>\n" +
			"<h3>Se escriben con J:</h3>\n" +
			"<ul>\n" +
			"  <li>la terminación -jería: conserjería, cerrajería, etc.;</li>\n" +
			"  <li>los tiempos de los verbos cuyo infinitivo lleva esa letra: cruje de crujir o trabaja de trabajar;</li>\n" +
			"  <li>las palabras derivadas de otras que llevan j: cajita de caja o herejía de hereje;</li>\n" +
			"  <li>las formas verbales con sonido je, ji, si los infinitivos correspondientes no llevan ni g ni j: distrajimos de distraer, dedujimos de deducir, dijimos de decir, etc.</li>\n" +
			"</ul>\n" +
			"<h2>Uso de la H.</h2>\n" +
			"<ul>\n" +
			"  <li>Los prefijos hidra-, hidro-, hiper- e hipo-: hidráulico, hidrógeno, hipérbole, hipócrita;</li>\n" +
			"  <li>Todas las palabras que empiezan por el diptongo ue: hueco, huelga, huella, huérfano, huerto; </li>\n" +
			"  <li>Los prefijos hecto-, hepta-, hexa- y hemi- de las palabras compuestas: hectómetro, heptasílabo, hexágono, hemiciclo;</li>\n" +
			"  <li>Los compuestos y derivados de palabras que tienen h, excepto los derivados de hueso, huevo, hueco y huérfano: escribimos huelguista, pero óseo, óvulo, oquedad y orfandad;</li>\n" +
			"  <li>Todas las formas de los verbos cuyo infinitivo lleva h: hubo, habla, honra, hurtaron.</li>\n" +
			"  <li>Palabras homófonas recogemos en esta tabla algunos pares de palabras homófonas en las que está implicada la letra h. Los términos homófonos son aquellos que suenan igual, pero tienen distinto significado y pueden tener distinta grafía.</li>\n" +
			"</ul>\n"
}
