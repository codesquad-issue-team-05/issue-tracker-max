import { styled } from 'styled-components';

export default function Main({ children }: { children: React.ReactElement }) {
  return (
    <Container>
      <h2 className="blind">메인</h2>
      {children}
    </Container>
  );
}

const Container = styled.main`
  padding: 0 80px;
`;
