import { useContext } from 'react';
import { Navigate } from 'react-router';
import { Routes, Route } from 'react-router-dom';
import { AppContext } from '../main';
import IssueList from '../pages/IssueList';

export default function Authenticate() {
  const { util } = useContext(AppContext);

  return util.isLogin() ? (
    <Routes>
      <Route path="/" element={<IssueList />} />
    </Routes>
  ) : (
    <Navigate to="/login" replace />
  );
}
