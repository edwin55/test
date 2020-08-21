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
	_cuantos: number = 1; //num elementos a mostrar
	
	// metodos
	constructor(private _http: HttpService) { }

	//consulta la api y guarda los pronosticos
	ngOnInit() {
		this._clima = this._http.getPronosticoClima()
		.subscribe( respuesta => {
			this._clima = respuesta;
			//console.log(this._clima);
		})
	}

	//muestra los siguientes pronosticos
	mostrar() {
		let max = (typeof this._clima['cnt'] === 'undefined' || this._clima['cnt'] === null) ? 0 : this._clima['cnt'];
		this._cuantos = (this._cuantos >= max) ? 1 : this._cuantos+5;
	}
}
