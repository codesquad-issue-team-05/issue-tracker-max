import { styled } from 'styled-components';
import FilterBar from './common/FilterBar';
import TabButton from './common/TabButton';
import ButtonSmall from './common/button/ButtonSmall';

export default function Toolbar() {
  return (
    <Container>
      <FilterBar />
      <ActionGroup>
        <TabButton />
        <ButtonSmall type="button" iconName="plus">
          이슈 작성
        </ButtonSmall>
      </ActionGroup>
    </Container>
  );
}

const Container = styled.div`
  padding: 24px 80px;
  display: flex;
  justify-content: space-between;
`;

const ActionGroup = styled.div`
  display: flex;
  gap: 16px;
`