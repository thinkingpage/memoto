import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Memo} from './memo';
import {config} from "./config"

@Injectable({
  providedIn: 'root'
})
export class MemoService {
  private apiServerUrl = config.apiBaseUrl;

  constructor(private http: HttpClient) { }
  public getMemos(): Observable<Memo[]> {
    console.log(this.apiServerUrl);
    return this.http.get<Memo[]>(`${this.apiServerUrl}/memos`)
  }

  public addMemo(memo: Memo): Observable<Memo> {
    return this.http.post<Memo>(`${this.apiServerUrl}/memos`, memo)
  }

  public getMemo(memoId: number): Observable<Memo> {
    return this.http.get<Memo>(`${this.apiServerUrl}/memos/${memoId}`)
  }

  public deleteMemo(memoId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/memos/${memoId}`)
  }
}
