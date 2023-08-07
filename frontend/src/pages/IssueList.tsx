import { useContext } from 'react';
import { AppContext } from '../main';
import Header from '../components/landmark/Header';
import Toolbar from '../components/Toolbar';
import IssueTable from '../components/IssueTable';
import Main from '../components/landmark/Main';
import { Link } from 'react-router-dom';
import ContextLogo from '../types/ContextLogo';

export default function IssueList() {
  const { util, control } = useContext(AppContext);
  const logo = (util.getLogoByTheme() as ContextLogo).medium;
  return (
    <>
      <Header>
        <Link to="/">
          <img src={logo} alt="이슈트래커" />
        </Link>
        <div>
          {/* profile */}
          popopo
        </div>
      </Header>
      <Toolbar />
      <Main>
        <IssueTable />
      </Main>
      {/* <button onClick={() => control.logoutCheck()}>logout</button> */}
    </>
  );
}
