import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { API_BASE_URL } from '../constants/constants';
import { RailwayDto } from 'src/app/model/railway.model';
import { SeatDto } from '../model/seat.model';

const httpOptions = {
  headers: new HttpHeaders({
    'Access-Control-Allow-Origin': '*'
  })
};

@Injectable({
  providedIn: 'root'
})
export class RailwayService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<RailwayDto[]> {
    return this.http.get<RailwayDto[]>(API_BASE_URL + "/railways");
  }

  getPageableView(filter: string, sortDirection: string, pageNumber: number, pageSize: number): Observable<RailwayDto[]> {
    return this.http.get<RailwayDto[]>(API_BASE_URL + "/railways/view?filter=" + filter
      + "&sortDirection=" + sortDirection + "&page=" + pageNumber + "&size=" + pageSize);
  }

  getAvailableSeats(railwayId: number): Observable<SeatDto[]> {
    return this.http.get<SeatDto[]>(API_BASE_URL + '/railways/' + railwayId + '/seats');
  }

  getCount(): Observable<number> {
    return this.http.get<number>(API_BASE_URL + "/railways/count");
  }

  get(railwayId: number): Observable<RailwayDto> {
    return this.http.get<RailwayDto>(API_BASE_URL + "/railways/" + railwayId);
  }

  create(railwayDto: RailwayDto): Observable<RailwayDto> {
    return this.http.post<RailwayDto>(API_BASE_URL + "/railways", railwayDto, httpOptions);
  }

  update(railwayDto: RailwayDto, railwayId: number): Observable<RailwayDto> {
    return this.http.put<RailwayDto>(API_BASE_URL + "/railways/" + railwayId, railwayDto, httpOptions);
  }

  delete(railwayId: number): Observable<any> {
    return this.http.delete(API_BASE_URL + "/railways/" + railwayId, httpOptions);
  }
}
