//test
//context.desde="2015-08-29";
//context.hasta="2015-08-31";
//empieza con la fecha default (dia de prod)
context.fechaInicio=context.fechaFin=TalendDate.parseDate("yyyy-MM-dd","2015-09-01");
//guarda la fecha de consulta
if(context.desde.isEmpty() || context.desde.equals("")){
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
    
}else if(context.hasta.isEmpty() || context.hasta.equals("") ){
    //genera la fecha de un dia a partir del inicio
    if(context.desde.matches("\\d{4}-\\d{2}-\\d{2}") ){
        Date fchbase=TalendDate.parseDate("yyyy-MM-dd",context.desde);
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
    }

}else if(!context.desde.isEmpty() && !context.hasta.isEmpty()){
    //valida fechas ingresadas y comprobar que hasta>desde
    if(context.desde.matches("\\d{4}-\\d{2}-\\d{2}") && context.hasta.matches("\\d{4}-\\d{2}-\\d{2}") && TalendDate.compareDate(TalendDate.parseDate("yyyy-MM-dd",context.hasta),TalendDate.parseDate("yyyy-MM-dd",context.desde))>-1 ){
        //guarda en vars de contexto
        context.fechaInicio=TalendDate.parseDate("yyyy-MM-dd",context.desde);
        context.fechaFin=TalendDate.parseDate("yyyy-MM-dd",context.hasta);
        //Cambia la fecha a "23:59:59"
        context.fechaFin = TalendDate.setDate(context.fechaFin,23,"HH");
        context.fechaFin = TalendDate.setDate(context.fechaFin,59,"mm");
        //resetea los segundos
        Calendar s = Calendar.getInstance();
        s.setTime(context.fechaFin);
        s.set(Calendar.SECOND,25);
        context.fechaFin=s.getTime();
    }else{
        //carga fecha default (dia de salida a prod)
        //context.fechaInicio=context.fechaFin="2015-09-01";
    }            
}

//codigo adicional
System.out.println("I:"+context.fechaInicio);
System.out.println("F:"+context.fechaFin);
//System.out.println(TalendDate.compareDate(context.fechaFin,context.fechaInicio));
