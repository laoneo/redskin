/*******************************************************************************
 * Redskin Shop is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Redskin Shop is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Redskin Shop.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Allon Moritz
 * @copyright 2007-2009 Allon Moritz
 * @version $Revision: 1.0.0 $
 *******************************************************************************/
package ch.allon.redskin.internal.ui.custom;

import java.util.ArrayList;

import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;

import ch.allon.redskin.core.IProductVisitor;
import ch.allon.redskin.core.ProductIndexer;
import ch.allon.redskin.core.model.shop.Product;

/**
 * @author Allon Moritz
 * 
 */
public class ProductProposalProvider implements IContentProposalProvider {

	@Override
	public IContentProposal[] getProposals(final String contents, final int position) {
		final ArrayList<IContentProposal> data = new ArrayList<IContentProposal>(
				10);
		ProductIndexer.visit(new IProductVisitor() {

			@Override
			public boolean visit(Product product) {
				if (product.getNumber().toString().startsWith(contents)) {
					data.add(new ProductProposal(product, position));
					if (data.size() == 9)
						return false;
				}
				return true;
			}
		});
		return (IContentProposal[]) data.toArray(new IContentProposal[data
				.size()]);
	}
}

class ProductProposal implements IContentProposal {

	private Product product;
	private int strip;

	/**
	 * @param product
	 * @param strip 
	 */
	public ProductProposal(Product product, int strip) {
		this.product = product;
		this.strip = strip;
	}

	@Override
	public String getContent() {
		return product.getNumber().toString().substring(strip);
	}

	@Override
	public int getCursorPosition() {
		return getContent().length();
	}

	@Override
	public String getDescription() {
		return product.getDescription();
	}

	@Override
	public String getLabel() {
		return product.getName() + " [" + product.getNumber() + "]"; //$NON-NLS-1$ //$NON-NLS-2$
	}

}