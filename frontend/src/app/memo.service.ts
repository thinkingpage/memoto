import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {MemoModel} from './models/memo.model';
import {MemoDTO} from './models/memo.dto.model';

@Injectable({
  providedIn: 'root'
})
export class MemoService {
  private apiServerUrl = "http://localhost:8081";

  constructor(private http: HttpClient) { }

  public getMemos(): Observable<MemoDTO[]> {
    return this.http.get<MemoDTO[]>(`${this.apiServerUrl}/memosdto`)
  }

  public addMemo(memo: MemoModel): Observable<MemoDTO> {
    return this.http.post<MemoDTO>(`${this.apiServerUrl}/memos`, memo)
  }

  public getMemo(memoId: number): Observable<MemoModel> {
    return this.http.get<MemoModel>(`${this.apiServerUrl}/memos/${memoId}`)
  }

  public deleteMemo(memoId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/memos/${memoId}`)
  }
}
