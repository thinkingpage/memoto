import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {MemoModel} from './models/memo.model';

@Injectable({
  providedIn: 'root'
})
export class MemoService {
  private apiServerUrl = "http://localhost:8081";

  constructor(private http: HttpClient) { }
  public getMemos(): Observable<MemoModel[]> {
    return this.http.get<MemoModel[]>(`${this.apiServerUrl}/memos`)
  }

  public addMemo(memo: MemoModel): Observable<MemoModel> {
    return this.http.post<any>(`${this.apiServerUrl}/memos`, memo)
  }

  public getMemo(memoId: number): Observable<MemoModel> {
    return this.http.get<MemoModel>(`${this.apiServerUrl}/memos/${memoId}`)
  }

  public deleteMemo(memoId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/memos/${memoId}`)
  }

  public login(): Observable<Object> {
    return this.http.get(`${this.apiServerUrl}/login`)
  }
}
