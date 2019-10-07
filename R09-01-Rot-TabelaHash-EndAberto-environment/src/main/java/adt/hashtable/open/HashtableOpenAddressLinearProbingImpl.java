package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends
		AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size,
			HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if(isFull()){
			throw new HashtableOverflowException();
		}
		else if (element != null) {
			int i = 0;
			boolean inseriu = false;
			boolean duplicado = false;
			while (i < capacity() && !inseriu && !duplicado) {
				int index = ((HashFunctionLinearProbing<T>) getHashFunction()).hash(element, i);
				if (this.table[index] == null || this.table[index] instanceof DELETED) {
					this.table[index] = element;
					this.elements++;
					inseriu = true;
				}
				else if(this.table[index].equals(element)){
					duplicado = true;
				}
				else {
					i++;
					this.COLLISIONS++;
				}
			}
		}
	}

	@Override
	public void remove(T element) {
		if(element != null && !isEmpty()) {
			int index = indexOf(element);
			if (index != -1) {
				this.table[index] = new DELETED();
				this.elements--;
			}
		}
	}

	@Override
	public T search(T element) {
		T retorno = null;
		if(element != null) {
			if (indexOf(element) != -1) {
				retorno = element;
			}
		}
		return retorno;
	}

	@Override
	public int indexOf(T element) {
		int retorno = -1;
		if (element != null && !isEmpty()) {
			int i = 0;
			boolean achou = false;
			while (i < this.table.length && !achou) {
				int index = ((HashFunctionLinearProbing<T>) getHashFunction()).hash(element, i);
				if (this.table[index] != null || (this.table[index] instanceof DELETED)) {
					if (this.table[index].equals(element)) {
						retorno = index;
						achou = true;
					} else {
						i++;
					}
				} else i++;
			}
		}
		return retorno;
	}

}
