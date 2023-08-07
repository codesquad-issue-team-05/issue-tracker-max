import { styled } from 'styled-components';

export default function Header({ children }: { children: React.ReactNode }) {
  return (
    <Container>
      <h1 className="blind">헤더</h1>
      {children}
    </Container>
  );
}

const Container = styled.header`
  height: 94px;
  padding: 0 80px;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  justify-content: space-between;
`;
