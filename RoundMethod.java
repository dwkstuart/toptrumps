public int playRound()
{
    int chosenCharacteristic = setCharacteristic(playerPointer);
    return getOutcome(chosenCharacteristic);
}

public int playRound(chosenCharacteristic)
{
    return getOutcome(chosenCharacteristic);
}


private int setCharacteristic(int playerPointer)
{
    Card currentCard = activePlayers[playerPointer].getCurrentCard();
    int currentCharacteristic = currentCard.getMaxCharacteristic();
    return currentCharacteristic; 
}

public int getOutcome(int characteristic)
{
    int[] characteristicValues = new int[activePlayers.length()];

    int outcome = 0;
    int max = 0;
    int numMaxValue = 0;

    for(int i = 0; i < activePlayers.length(); i++)
    {
        if(activePlayers[i] != null) //This may have to be adjusted to refer to a variable in Player
        {
            Card currentCard = activePlayers[i].getCurrentCard();
            characteristicValues[i] =  currentCard.getCharacteristicValueAt(characteristic);
        }
    } 

    for(int i = 0; i < characteristicValues.length(); i++)
    {
        if(characteristicValues[i] > max)
        {
            max = characteristicValues[i];
            outcome = i;
        }

    }

     for(int i = 0; i < characteristicValues.length(); i++)
    {
        if(characteristicValues[i] == max)
        {
            numMaxValue++;
            if(numMaxValue > 1)
            {
                return -1;
            }
        }
    }

    return outcome;
      
}


