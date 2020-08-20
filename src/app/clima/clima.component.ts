import { Component, OnInit } from '@angular/core';
//agrega configuracion
import {HttpService} from '../config/http.service';

@Component({
  selector: 'app-root', //selector: 'app-clima',
  templateUrl: './clima.component.html',
  styleUrls: ['./clima.component.scss']
})
export class ClimaComponent implements OnInit {
	//atributos
	_clima: Object; //almacena data de la api
	
	// metodos
	constructor(private _http: HttpService) { }

	//consulta la api y guarda los datos
	ngOnInit() {
		this._clima = this._http.getPronosticoClima()
		.subscribe( respuesta => {
			this._clima = respuesta;
			console.log(this._clima);
		})
	}

}