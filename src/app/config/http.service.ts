import { Injectable } from '@angular/core';
//agregados
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import apiConfig from '../../assets/apiConfig.json';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private _http: HttpClient) { }

  //Obtiene el pronostico de clima desde open weather
  getPronosticoClima(){
  	let url = apiConfig.urlBase + "?id=" + apiConfig.pais.id + "&appid=" + apiConfig.pais.appid;
  	return this._http.get(url);
  }

}
