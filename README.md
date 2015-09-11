Talend 6.0.0
====

ejemplo de job talend
Pasa parametros por la url y generara la fecha del sistema usando tJava
https://www.talendforge.org/tutorials/tutorial.php?idTuto=38

//Obtiene la fecha del servidor
Date fchbase=TalendDate.getCurrentDate();
//Inicial: Guarda la fecha de ayer
context.fechaInicio = TalendDate.addDate(fchbase,-1,"dd");
//Cambia la fecha a "00:00:00"
context.fechaInicio=TalendDate.setDate(context.fechaInicio,0,"HH");
context.fechaInicio=TalendDate.setDate(context.fechaInicio,0,"mm");
//resetea los segundos
Calendar c = Calendar.getInstance();
c.setTime(context.fechaInicio);
c.set(Calendar.SECOND,0);
context.fechaInicio=c.getTime();

//Final: guarda fecha de ayer
context.fechaFin = TalendDate.addDate(fchbase,-1,"dd");
//Cambia la fecha a "23:59:59"
context.fechaFin = TalendDate.setDate(context.fechaFin,23,"HH");
context.fechaFin = TalendDate.setDate(context.fechaFin,59,"mm");
//resetea los segundos
Calendar s = Calendar.getInstance();
s.setTime(context.fechaFin);
s.set(Calendar.SECOND,59);
context.fechaFin=s.getTime();
