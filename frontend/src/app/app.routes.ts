import { Routes } from '@angular/router';
import { MemoAllMemosComponent } from './components/memos/memo-all-memos.component';
import { MemoAddMemo } from './components/memos/memo-add-memo';
import { MemoItemComponent } from './components/memos/memo-item.component';
import {NotFoundComponent} from './components/not-found.component';

export const routes: Routes = [
  {
    path: 'memos',
    component: MemoAllMemosComponent,
  },
  {
    path: 'add',
    component: MemoAddMemo,
  },
  {
    path: 'memo/:memoId',
    component: MemoItemComponent
  },
  {
    path: '**',
    component: NotFoundComponent
  },

];
