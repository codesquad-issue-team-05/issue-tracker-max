import { styled } from 'styled-components';
import { useState } from 'react';
import Button from './button/BaseButton';

enum ButtonActive {
  Default,
  Left,
  Right,
}

const { Default, Left, Right } = ButtonActive;

export default function TabButton() {
  const [buttonActive, setButtonActive] = useState<ButtonActive>(Default);
  return (
    <Container $buttonActive={buttonActive}>
      <Button
        type="button"
        flexible
        ghost={true}
        iconName="plus"
        onClick={() => setButtonActive(Left)}>
        BUTTON
      </Button>
      <Vertical />
      <Button
        type="button"
        ghost={true}
        flexible
        iconName="plus"
        onClick={() => setButtonActive(Right)}>
        BUTTON
      </Button>
    </Container>
  );
}

const Container = styled.div<{ $buttonActive: ButtonActive }>`
  width: 320px;
  display: inline-flex;
  border: 1px solid ${({ theme }) => theme.color.neutral.border.default};
  border-radius: ${({ theme }) => theme.objectStyles.radius.medium};
  overflow: hidden;

  & > button {
    width: 100%;
    box-sizing: content-box;
    &:first-child {
      background-color: ${({ theme, $buttonActive }) => {
        return $buttonActive === Left
          ? theme.color.neutral.surface.bold
          : theme.color.neutral.surface.default;
      }};
    }
    &:last-child {
      background-color: ${({ theme, $buttonActive }) => {
        return $buttonActive === Right
          ? theme.color.neutral.surface.bold
          : theme.color.neutral.surface.default;
      }};
    }
  }
`;

const Vertical = styled.div`
  width: 1px;
  flex-shrink: 0;
  background-color: ${({ theme }) => theme.color.neutral.border.default};
`;
